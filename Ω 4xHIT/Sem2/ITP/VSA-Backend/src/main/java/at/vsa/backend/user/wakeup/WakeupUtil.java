package at.vsa.backend.user.wakeup;

import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WakeupUtil {

    private final UserRepository userRepository;
    public boolean isValidToken(String email, String token){
        Optional<User> userOptional = userRepository.findByEmailAndWakeuptoken(email, token);
        if (userOptional.isPresent()) {
            userOptional.get().setLastverification(LocalDateTime.now());
            userOptional.get().setWakeuptoken(null);
            userRepository.save(userOptional.get());

            return true;
        }
        return false;
    }

    public String getToken(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        String token = null;
        if (userOptional.isPresent()) {
            token = userOptional.get().getWakeuptoken();
        }
        return token;
    }
}
