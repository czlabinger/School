package at.zk.Datenmanagement.user.auth;

import at.zk.Datenmanagement.user.UserRepository;
import at.zk.Datenmanagement.user.UserServiceImpl;
import at.zk.Datenmanagement.user.auth.requests.SigninRequest;
import at.zk.Datenmanagement.user.auth.requests.RegisterRequest;
import at.zk.Datenmanagement.user.entities.Role;
import at.zk.Datenmanagement.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileReader;
import java.io.IOException;

@Controller
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationServiceImpl authService;
    private final UserServiceImpl userService;

    public AuthController(AuthenticationServiceImpl authService, UserServiceImpl userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authService = authService;
        this.userService = userService;
        this.userService.clearDB();

        try (FileReader reader = new FileReader("default_users.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);
            System.out.println(jsonArray.toString(4));

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Role role = Role.READER;
                switch(jsonObject.getString("role")) {
                    case "admin":
                        role = Role.ADMIN;
                        break;

                    case "moderator":
                        role = Role.MODERATOR;
                        break;

                    case "reader":
                        role = Role.READER;
                        break;
                }

                var user = User.builder()
                        .name(jsonObject.getString("name"))
                        .email(jsonObject.getString("userid"))
                        .password(passwordEncoder.encode(jsonObject.getString("password")))
                        .role(role)
                        .build();

                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/auth/admin/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return authService.register(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest request) {
        return authService.signin(request);
    }

    @PostMapping("/auth/verify")
    public ResponseEntity<?> verify(HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok().body("User " + user.getEmail() + " with role: " + user.getRole() + " verified.");
    }

}
