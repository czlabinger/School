package at.vsa.backend.email;

import at.vsa.backend.user.account_recovery.ResetTokenUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;



public class EmailServiceImpl {

    private final JavaMailSender emailSender;

    private final ResetTokenUtil resetTokenUtil;
    public EmailServiceImpl(ResetTokenUtil rt) {
        resetTokenUtil = rt;
        MailConfig mailConfig = new MailConfig();
        emailSender = mailConfig.getJavaMailSender();
    }
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@vsa.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        // ...
    }

    public void sendPasswordReset(String email) {
        //Testweise nur Email übergabe
        //Username würde von Frontend kommen
        String token =  resetTokenUtil.createToken(email);
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            helper.setFrom("noreply@vsa.com");
            helper.setTo(email);
            helper.setSubject("Password reset request");
            helper.setText("<h1>Password reset</h1> <br></br> You send a password reset request. Follow the <a href=\"localhost:8080/resetPassword?usernameOrEmail="+email+"&token="+token+"\">link</a>", true);
            emailSender.send(mimeMessage);
        } catch (jakarta.mail.MessagingException jmme) {
            System.out.println(jmme.getStackTrace());
        }
    }

}
