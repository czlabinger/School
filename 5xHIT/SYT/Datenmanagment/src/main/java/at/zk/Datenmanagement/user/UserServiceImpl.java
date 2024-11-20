package at.zk.Datenmanagement.user;

import at.zk.Datenmanagement.user.auth.JWTService;
import at.zk.Datenmanagement.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public User getUserFromRequest(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;

        final String jwt = authHeader.substring("Bearer ".length());
        final String email = jwtService.extractEmail(jwt);
        if (email == null) return null;

        User user = userRepository.findByEmail(email).orElse(null);

        return user;
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?]).{8,}$");
    }

    @Override
    public UserDetailsService userDetailService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public void clearDB() {
        userRepository.deleteAll();
    }
}

