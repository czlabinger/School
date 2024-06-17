package at.vsa.backend.user;

import at.vsa.backend.FileHelper;
import at.vsa.backend.user.authentication.config.JwtService;
import at.vsa.backend.user.entities.Role;
import at.vsa.backend.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static at.vsa.backend.BackendApplication.BUCKET_NAME;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserFromRequest(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        final String jwt = authHeader.substring("Bearer ".length());
        final String email = jwtService.extractEmail(jwt);
        if(email == null) return null;

        User user = userRepository.findByEmail(email).orElse(null);

        return user;
    }

    public boolean changeEmailOfUser(final User user, final String newEmail) {
        if (!(user.getEmail().equalsIgnoreCase(newEmail))) {
            if (userRepository.findByEmail(newEmail).isEmpty()) {
                user.setEmail(newEmail);
                userRepository.save(user);
                return true;
            }
        }

        return false;
    }

    public boolean changePasswordOfUser(final User user, final String newPassword) {

        if(!isValidPassword(newPassword)) {
            System.out.println("ChangePassword: Password not valid");
            return false;
        }

        final String pepperedNewPassword = User.pepper + newPassword;

        // Update the user's password
        user.setPassword(passwordEncoder.encode(pepperedNewPassword));
        userRepository.save(user);

        return true;
    }

    public boolean changeSubscriptionOfUser(final User u, final Role r) {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/currentVersions.json"));
        } catch (ParseException | IOException e) {
            System.err.println("Error when changing subsucription for user: " + u.getEmail() + "\n" + e.getStackTrace());
            return false;
        }

        u.setRole(r);
        u.setSubversion(((Long) jsonObject.get(r.name())).intValue()); //TODO: didn't completely look through it but this doesn't seem right
        userRepository.save(u);

        return true;
    }

    public int getFilesizeLimitOfUser(final User user) {
        return user.getRole().getFilesizeLimit(user);
    }

    //TODO: document if filesize is KB, MB or whatever @Christof Zlabinger
    public boolean isValidFilesize(final User user, long filesize) {
        return filesize <= getFilesizeLimitOfUser(user);
    }

    //TODO: recheck and test this method - honestly
    //TODO: rename this method (no idea what it does)
    // seems like it counts the amount of files instead of their size
    public boolean checkFileLimit(User user) {

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()) {
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(user.getEmail())
                    .build();

            ListObjectsResponse listing = s3.listObjects(listObjectsRequest);
            List<S3Object> objects = listing.contents();

            HashMap<String, Long> uploadedFilesMap = new HashMap<>();

            for (S3Object object : objects) {
                uploadedFilesMap.put(object.key(), object.size());
            }
            return uploadedFilesMap.size() < user.getRole().getFileLimit(user);
        }
    }

    @Deprecated
    // Use isValidPasswordRegex(String password) instead
    public static boolean isValidPassword(String password) {
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;
        boolean specialCharacterPresent = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            currentCharacter = password.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            } else if (specialChars.contains(String.valueOf(currentCharacter))) {
                specialCharacterPresent = true;
            }
        }

        return numberPresent && upperCasePresent && lowerCasePresent && specialCharacterPresent;
    }

    public void unlock(User user) {
        user.setLocked(false);
        user.setWronglogin(0);
        userRepository.save(user);
    }

    //TODO: didn't test this method yet @Christof Zlabinger
    public static boolean isValidPasswordRegex(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+\\\\|[{]};:'\",<.>/?]).{8,}$");
    }
}