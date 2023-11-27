package at.vsa.backend;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    private static final String pepper = "3prs%GFh86x1B*EKnO@367JSYz^VPv@ztqh5pXNs";
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean createNewUser(final String username, final String email, final String password) {

        final String pepperedPassword = password + pepper;

        final String hashedPassword = hashPassword(pepperedPassword);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        userRepository.save(user);

        System.out.println(verifyPassword(pepperedPassword, hashedPassword));

        return true;
    }

    public boolean verifyPassword(final String pepperedPassword, final String storedHashedPassword) {
        return passwordEncoder.matches(pepperedPassword, storedHashedPassword);
    }

    private String hashPassword(final String passwordToHash) {
        return passwordEncoder.encode(passwordToHash);
    }

    protected boolean authenticate(String username, String password){
        User u = loadUserByUsername(username);
        if(u == null) { throw new UsernameNotFoundException(username); }

        return verifyPassword(password + pepper,u.getPassword());
    }

    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}