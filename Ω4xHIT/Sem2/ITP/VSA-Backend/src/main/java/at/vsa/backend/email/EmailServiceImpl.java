package at.vsa.backend.email;

import at.vsa.backend.assets.LinkTokenHelper;
import at.vsa.backend.user.account_recovery.ResetTokenUtil;
import at.vsa.backend.user.entities.User;
import at.vsa.backend.user.wakeup.WakeupUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static at.vsa.backend.BackendApplication.BUCKET_NAME;
import static at.vsa.backend.BackendApplication.FROM;

@Service //TODO: was Component - didn't test with @Service yet
@AllArgsConstructor
public class EmailServiceImpl {

    private final TemplateEngine templateEngine;
    private final JavaMailSender emailSender;
    private final ResetTokenUtil resetTokenUtil;
    private final WakeupUtil wakeupUtil;
    private final LinkTokenHelper linkTokenHelper;

    @Deprecated
    public void testSimpleEmailMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        // ...
    }

    public boolean sendPasswordReset(User user) {

        if(user == null) return false;

        String token = resetTokenUtil.createResetTokenForUser(user);
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setFrom(FROM, "Noreply VSA");
            helper.setTo(user.getEmail());
            helper.setSubject("Password reset request");
            Context context = new Context();
            context.setVariable("username", user.getEmail());
            context.setVariable("resetLink", "http://localhost:3000/resetpassword?email=" + user.getEmail() + "&token=" + token); //TODO: update link to static in main
            String emailContent = templateEngine.process("passwordReset", context);
            helper.setText(emailContent, true);
            emailSender.send(mimeMessage);

            return true;

        } catch (jakarta.mail.MessagingException | UnsupportedEncodingException jmme) {
            System.err.println(jmme.getStackTrace());
        }

        return false;
    }

    public void sendSuccessfulPasswordChange(User user) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setFrom(FROM, "Noreply VSA");
            helper.setTo(user.getEmail());
            helper.setSubject("Password changed successfully");
            Context context = new Context();
            context.setVariable("username", user.getEmail());
            String emailContent = templateEngine.process("successfulPasswordReset", context);
            helper.setText(emailContent, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException jmme) {
            System.out.println(jmme.getStackTrace());
        }
    }

    public void sendSuccessfulRegistration(User user) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setFrom(FROM, "Noreply VSA");
            helper.setTo(user.getEmail());
            helper.setSubject("Successful Registration");
            Context context = new Context();
            context.setVariable("username", user.getEmail());
            String emailContent = templateEngine.process("successfulRegistration", context);
            helper.setText(emailContent, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException jmme) {
            System.out.println(jmme.getStackTrace());
        }
    }

    public void sendWakeUpEmail(String email) {
        String token = wakeupUtil.getToken(email);
        if (token != null) {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            try {
                helper.setFrom(FROM, "Noreply VSA");
                helper.setTo(email);
                helper.setSubject("Confirm your Presence");
                helper.setPriority(3);
                Context context = new Context();
                context.setVariable("username", email);
                context.setVariable("verificationLink", "http://localhost:8080/wakeup/verify?email=" + email + "&token=" + token);
                String emailContent = templateEngine.process("wakeUp", context);
                helper.setText(emailContent, true);
                emailSender.send(mimeMessage);
            } catch (MessagingException | UnsupportedEncodingException jmme) {
                System.out.println(jmme.getStackTrace());
            }
        }
    }

    public void sendSecondWakeUpEmail(String email) {
        String token = wakeupUtil.getToken(email);
        if (token != null) {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            try {
                helper.setFrom(FROM, "Noreply VSA");
                helper.setTo(email);
                helper.setSubject("Confirm your Presence");
                helper.setPriority(1);
                Context context = new Context();
                context.setVariable("username", email);
                context.setVariable("verificationLink", "http://localhost:8080/wakeup/verify?email=" + email + "&token=" + token);
                String emailContent = templateEngine.process("secondWakeUp", context);
                helper.setText(emailContent, true);
                emailSender.send(mimeMessage);
            } catch (MessagingException | UnsupportedEncodingException jmme) {
                System.out.println(jmme.getStackTrace());
            }
        }
    }

    public void sendAssetsToContacts(String originalEmail) {
        ArrayList<String> contacts = new ArrayList<>();
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()){
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(originalEmail)
                    .build();

            ListObjectsResponse listing = s3.listObjects(listObjectsRequest);
            List<S3Object> objects = listing.contents();

            for (S3Object object : objects) {
                contacts.add(object.key().split("/")[1]);
            }
        }

        for(String contact : contacts) {
            try {
                helper.setFrom(FROM, "Noreply VSA");
                helper.setTo(contact);
                helper.setSubject("Dead");

                Context context = new Context();
                context.setVariable("username", contact);
                context.setVariable("link", linkTokenHelper.createVsaLink(originalEmail, contact));
                String emailContent = templateEngine.process("assets", context);
                helper.setText(emailContent, true);
                emailSender.send(mimeMessage);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendInvoice(String receiver, String username, String invoiceLink) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setFrom(FROM, "Noreply VSA");
            helper.setTo(receiver);
            helper.setSubject("New invoice");
            Context context = new Context();
            context.setVariable("invoiceLink", invoiceLink);
            context.setVariable("username", username);
            String emailContent = templateEngine.process("invoiceMail", context);
            helper.setText(emailContent, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException jmme) {
            System.out.println(jmme.getStackTrace());
        }
    }
}
