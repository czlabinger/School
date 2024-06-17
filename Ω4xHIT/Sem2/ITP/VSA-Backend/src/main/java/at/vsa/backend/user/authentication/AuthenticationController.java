package at.vsa.backend.user.authentication;

import at.vsa.backend.JsonHelper;
import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.UserService;
import at.vsa.backend.user.account_recovery.ResetTokenUtil;
import at.vsa.backend.user.authentication.config.AuthenticationService;
import at.vsa.backend.user.authentication.config.Country;
import at.vsa.backend.user.authentication.protocols.request.*;
import at.vsa.backend.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") //TODO: cross origins
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final ResetTokenUtil resetTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return service.register(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return service.authenticate(request);
    }

    @PostMapping("/changeemail")
    public ResponseEntity<?> changeEmail(HttpServletRequest request, @RequestBody ChangeEmailRequest changeEmailRequest) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return null;

        boolean successful = userService.changeEmailOfUser(user, changeEmailRequest.getNewEmail());

        if(successful) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequest changePasswordRequest) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return null;

        boolean successful = userService.changePasswordOfUser(user, changePasswordRequest.getPassword());

        if(successful) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/changeinterval")
    public ResponseEntity<?> changeInterval(HttpServletRequest request, @RequestBody ChangeIntervalRequest authentication) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return null;

        user.setVerificationinterval(authentication.getInterval());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    /**
     * Used to check if user is logged in if returned 200 logged in if 403 not loggen in
     * (let us cook)
     */
    @GetMapping("/checklogin")
    public ResponseEntity<?> checkLogin(HttpServletRequest request) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getuserdata")
    public ResponseEntity<?> getUserData(HttpServletRequest request) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity
                .status(403).body("Invalid JWT");

        HashMap<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("birthDate", user.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        map.put("country", Country.getCountryByCC(user.getCountry().getCc()).getName());
        map.put("interval", String.valueOf(user.getVerificationinterval()));
        map.put("fileLimit", String.valueOf(user.getRole().getFileLimit(user)));

        return ResponseEntity
                .ok().body(JsonHelper.toJSON(map));
    }

    @CrossOrigin(origins = "*") //TODO: cross origins
    @PostMapping("forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {

        System.out.println("email: " + forgotPasswordRequest.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if(optionalUser.isEmpty()) {
            System.out.println("User with this email doesn't exist");
            return ResponseEntity.ok().build(); //so that the frontend doesn't know whether a user with this email exists
        }

        User user = optionalUser.get();

        boolean successful = emailService.sendPasswordReset(user);
        System.out.println("Password reset email sent to " + user.getEmail());
        if(!successful) {
            System.err.println("Password reset email failed");
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody  ResetPasswordRequest resetPasswordRequest) {

        Optional<User> optionalUser = userRepository.findByEmail(resetPasswordRequest.getEmail());
        if(optionalUser.isEmpty()) return ResponseEntity.badRequest().build();

        User user = optionalUser.get();

        boolean correctResetToken = resetTokenUtil.isValidResetTokenForUser(user, resetPasswordRequest.getToken());
        if(!correctResetToken) return ResponseEntity.badRequest().build();

        boolean successful = userService.changePasswordOfUser(user, resetPasswordRequest.getNewPassword());

        if(!successful) return ResponseEntity.badRequest().build(); //for example not valid password

        resetTokenUtil.deleteResetTokenForUser(user);

        emailService.sendSuccessfulPasswordChange(user);

        userService.unlock(user);

        return ResponseEntity.ok().build();
    }
}
