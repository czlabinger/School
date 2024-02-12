package at.vsa.backend.user.account_recovery;

import at.vsa.backend.email.EmailServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRecoveryController {
    private final EmailServiceImpl mail;

    //Hilfs Klasse um Passwort-Reset-Token zu behandeln (erstellen, abfragen)
    private final ResetTokenUtil resetTokenUtil;

    public AccountRecoveryController() {
        resetTokenUtil = new ResetTokenUtil(); //TODO: think about making ResetToken static class
        mail = new EmailServiceImpl(resetTokenUtil);
    }

    @GetMapping("/email/resetPassword")
    public String tokenVal(@RequestParam String usernameOrEmail, @RequestParam String token) {
        if (resetTokenUtil.isValidToken(usernameOrEmail, token)) {
            System.out.println("Gültiger Token wurde übergeben");
            return "redirect:/reset-password-form";
        } else {
            System.out.println("Übergebener Token ist nicht gültig");
            return "redirect:/error";
        }
    }
}