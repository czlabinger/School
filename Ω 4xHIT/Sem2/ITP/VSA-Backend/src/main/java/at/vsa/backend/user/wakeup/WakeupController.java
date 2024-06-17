package at.vsa.backend.user.wakeup;

import at.vsa.backend.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WakeupController {

    private final WakeupUtil wakeupUtil;

    public WakeupController(WakeupUtil wakeupUtil) {
        this.wakeupUtil = wakeupUtil;
    }
    @PostMapping("/wakeup/verify")
    public ResponseEntity<?> tokenVal(@RequestParam String email, @RequestParam String token) {
        String redirectURL;

        if (wakeupUtil.isValidToken(email, token)) {
            System.out.println("Gültiger Token wurde übergeben");
            redirectURL = "http://localhost/successful-period-reset";
        }else {
            System.err.println("Ungültiger Token wurde übergeben");
            redirectURL = "http://localhost/error";
        }

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("email", email);
        queryParameters.put("token", token);

        return WebUtils.createRedirectResponse(redirectURL, queryParameters);

//        return WebUtils.createRedirectResponse(redirectURL, queryParameters);
    }
}
