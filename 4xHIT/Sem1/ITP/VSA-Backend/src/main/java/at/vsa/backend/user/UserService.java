package at.vsa.backend.user;

import at.vsa.backend.user.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    private static final String pepper = "3prs%GFh86x1B*EKnO@367JSYz^VPv@ztqh5pXNs"; //TODO: implement
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public boolean changePassword(final String usernameOrEmail, final String oldPassword, final String newPassword) {
//        User u = getUserByUsernameOrEmail(usernameOrEmail);
//
//        if(u == null) {
//            throw new UsernameNotFoundException(usernameOrEmail);
//        }
//
//        if(authenticate(u.getUsername(), oldPassword)) {
//            u.setPassword(newPassword);
//            userRepository.save(u);
//            return true;
//        }
//
//        return false;
//    }

    public User getUserByEmail(final String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return user;
    }

    public boolean verifyPassword(final String pepperedPassword, final String storedHashedPassword) {
        return passwordEncoder.matches(pepperedPassword, storedHashedPassword);
    }

    private String hashPassword(final String passwordToHash) {
        return passwordEncoder.encode(passwordToHash);
    }

    public boolean authenticate(String email, String password){
        User u = loadUserByEmail(email);
        if(u == null) { throw new UsernameNotFoundException(email); }

        return verifyPassword(password + pepper,u.getPassword());
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No User with Email: " + email));
    }
}