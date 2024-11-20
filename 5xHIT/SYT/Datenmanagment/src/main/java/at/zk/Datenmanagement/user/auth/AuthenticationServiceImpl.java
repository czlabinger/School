package at.zk.Datenmanagement.user.auth;


import at.zk.Datenmanagement.user.UserRepository;
import at.zk.Datenmanagement.user.auth.requests.SigninRequest;
import at.zk.Datenmanagement.user.auth.requests.RegisterRequest;
import at.zk.Datenmanagement.user.entities.Role;
import at.zk.Datenmanagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

import static at.zk.Datenmanagement.user.UserServiceImpl.isValidPassword;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(RegisterRequest request) throws IOException {

        if(!isValidPassword(request.getPassword())) {
            System.out.println("Password not valid");
            return ResponseEntity.badRequest().body("Password is not valid.");
        }

        if(this.userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            System.out.println("User with email: " + request.getEmail() + " already exists!");

            return ResponseEntity.status(409).body("User with email: " + request.getEmail() + " already exists!");
        }

        final String pepperedPassword = User.pepper + request.getPassword();

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(pepperedPassword))
                .name(request.getName())
                .role(Role.READER)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().body("Added user with email: " + request.getEmail());
    }

    public ResponseEntity<?> signin(SigninRequest request) {

        final String pepperedPassword = User.pepper + request.getPassword();

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("No User with Email: " + request.getEmail()));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        pepperedPassword
                )
        );

        String jwtToken = jwtService.generateToken(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwtToken);

        return ResponseEntity.status(200)
                .body(new JSONObject(map).toString());
    }
}