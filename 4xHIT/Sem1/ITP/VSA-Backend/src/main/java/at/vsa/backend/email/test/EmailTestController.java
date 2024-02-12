package at.vsa.backend.email.test;

import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.account_recovery.ResetTokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {

    private EmailServiceImpl mail;
    private ResetTokenUtil resetTokenUtil;

    public EmailTestController() {
        resetTokenUtil = new ResetTokenUtil();
        mail = new EmailServiceImpl(resetTokenUtil);
    }

    @RequestMapping("/email/test")
    public String mailSend() {
        mail.sendSimpleMessage("test@test.co", "Test Mail", "Test Mail Spring Mail");
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Email send successfully</h1>";
    }

    @RequestMapping("/email/sendPasswordReset")
    public String passwordReset() {
        mail.sendPasswordReset("test@test.co");
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Reset request send successfully</h1>";
    }
}
