package at.vsa.backend.user.wakeup;

import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestWakeUpController {
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @GetMapping("/wakeup/test")
    public String testWakeup() {
        for (User u : userRepository.findAll()) {
            if (ChronoUnit.DAYS.between(u.getLastverification(), LocalDateTime.now()) > 1) {
                //check if it is second email
                if (ChronoUnit.DAYS.between(u.getLastverification(), LocalDateTime.now()) > 31) {
                    emailService.sendSecondWakeUpEmail(u.getEmail());
                    return "<h1>Second wakeUp email sent</h1>";
                }
                String token = String.valueOf(UUID.randomUUID());
                u.setWakeuptoken(token);
                userRepository.save(u);
                emailService.sendWakeUpEmail(u.getEmail());
            }
        }
        return "<h1>Wakeup token for user created</h1>";
    }



}
