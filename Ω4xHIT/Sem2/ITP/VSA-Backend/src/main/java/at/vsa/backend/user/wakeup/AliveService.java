package at.vsa.backend.user.wakeup;

import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AliveService {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void checkInterval() {
        for (User u : userRepository.findAll()) {
            if (ChronoUnit.MINUTES.between(u.getLastverification(), LocalDateTime.now()) > u.getVerificationinterval() && u.getWakeupcount() >= 2 && !u.isDead()) {
                emailService.sendAssetsToContacts(u.getEmail());
                u.setDead(true);
                userRepository.save(u);
            }

            if (ChronoUnit.MINUTES.between(u.getLastverification(), LocalDateTime.now()) > u.getVerificationinterval() && !u.isDead()) {
                String token = String.valueOf(UUID.randomUUID());
                u.setWakeuptoken(token);
                u.setWakeupcount(u.getWakeupcount() + 1);
                userRepository.save(u);
                emailService.sendWakeUpEmail(u.getEmail());
            }
        }
    }
}
