package at.vsa.backend.email.test;

import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO: delete when not needed anymore
@Deprecated
@RestController
@AllArgsConstructor
public class EmailTestController {

    private final EmailServiceImpl emailService;
    private final UserRepository userRepository;

    @RequestMapping("/email/test")
    public String mailSend() {
        emailService.testSimpleEmailMessage("placeholder@test.com", "Test Mail", "Test Mail Spring Mail");
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Email send successfully</h1>";
    }

    @RequestMapping("/email/sendPasswordReset")
    public String passwordReset() {
        User u = userRepository.findByEmail("placeholder@test.com").orElseThrow(() -> new UsernameNotFoundException("test controller: placeholder@test.com"));
        emailService.sendPasswordReset(u);
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Reset request send successfully</h1>";
    }

    @RequestMapping("/email/sendRegistration")
    public String emailRegistration() {
        User u = userRepository.findByEmail("placeholder@test.com").orElseThrow(() -> new UsernameNotFoundException("test controller: placeholder@test.com"));
        emailService.sendSuccessfulRegistration(u);
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Registration request mail send successfully</h1>";
    }

    @RequestMapping("/email/sendSuccessfulPasswordReset")
    public String successfulPasswordReset() {
        User u = userRepository.findByEmail("placeholder@test.com").orElseThrow(() -> new UsernameNotFoundException("test controller: placeholder@test.com"));
        emailService.sendSuccessfulPasswordChange(u);
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Password changed successful request mail send successfully</h1>";
    }

    @RequestMapping("/email/wakeup")
    public String wakeup() {
        emailService.sendWakeUpEmail("bp03@aon.at");
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Wakeup successful</h1>";
    }

    @RequestMapping("/email/sendAsset")
    public String sendAsset(@RequestParam String email) {
        emailService.sendAssetsToContacts(email);
        return "<h1 style=\"color: green; font-family: arial; text-align:center\">Assets from " + email + " send successfully</h1>";
    }
}
