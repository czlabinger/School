package at.vsa.backend.user.authentication.config;

import at.vsa.backend.FileHelper;
import at.vsa.backend.JsonHelper;
import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.payment.PaymentUtil;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.authentication.protocols.request.AuthenticationRequest;
import at.vsa.backend.user.authentication.protocols.request.RegisterRequest;
import at.vsa.backend.user.entities.Role;
import at.vsa.backend.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static at.vsa.backend.user.UserService.isValidPassword;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl emailService;
    private final PaymentUtil paymentUtil;

    public ResponseEntity<?> register(RegisterRequest request) throws IOException {

        if(!isValidPassword(request.getPassword())) {
            System.out.println("Password not valid");
            return ResponseEntity.badRequest().body("Password is not valid.");
        }

        if(!Country.isValid(request.getCountry()))  {
            System.out.println("Not available in country");
            return ResponseEntity.badRequest().body("Not available in your country.");
        }

        if(this.userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("User with email: " + request.getEmail() + " already exists!");

            return ResponseEntity.status(409).body("User with email: " + request.getEmail() + " already exists!");
        }

        final String pepperedPassword = User.pepper + request.getPassword();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(request.getBirthdate(), formatter);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/currentVersions.json"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Long basicVersion = (Long) jsonObject.get("BASIC");

        var user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .birthdate(dateTime)
                .password(passwordEncoder.encode(pepperedPassword))
                .role(Role.BASIC)
                .country(Country.getCountryByCC(request.getCountry()))
                .subversion(basicVersion.intValue())
                .lastverification(LocalDateTime.now())
                .verificationinterval(90)
                .wakeupcount(0)
                .wronglogin(0)
                .locked(false)
                .dead(false)
                .build();

        userRepository.save(user);

        paymentUtil.createCustomer(user);
        emailService.sendSuccessfulRegistration(user);

        var jwtToken = jwtService.generateToken(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwtToken);

        return ResponseEntity.ok().body(JSONObject.toJSONString(map));
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {

        final String pepperedPassword = User.pepper + request.getPassword();

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("No User with Username: " + request.getEmail()));

        user.setWronglogin(user.getWronglogin() + 1);

        if(user.getWronglogin() >= 5) {
            user.setLocked(true);
            emailService.sendPasswordReset(user);
            userRepository.save(user);

            return ResponseEntity.status(403)
                    .body("Account is locked");
        }

        user.setWronglogin(0);
        user.setDead(false);
        user.setLastverification(LocalDateTime.now());
        userRepository.save(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        pepperedPassword
                )
        );

        String jwtToken = jwtService.generateToken(user);

        user.setWronglogin(0);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwtToken);

        return ResponseEntity.status(200)
                .body(JsonHelper.toJSON(map));
    }
}
