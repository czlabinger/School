package at.vsa.backend.payment;

import at.vsa.backend.JsonHelper;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.UserService;
import at.vsa.backend.user.entities.User;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PaymentController {

    private final WebhookUtil webhookUtil;
    private final PaymentUtil paymentUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    @Deprecated
    @PostMapping("/payment/register")
    public boolean customerRegister(@RequestParam String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) return false;
            System.out.println(paymentUtil.createCustomer(user.get()));
            return true;
        } catch (IllegalArgumentException se) {
            System.out.println(se.getMessage());
            return false;
        }
    }

    @GetMapping("/payment/checkout")
    public void checkout(HttpServletResponse httpServletResponse, @RequestParam String plan, @RequestParam String period, @RequestParam String customer) {
        try {
            String customerID = userRepository.findByEmail(customer).get().getStripeid();
            httpServletResponse.setHeader("Location", paymentUtil.checkoutSession(plan, period, customerID));
            httpServletResponse.setStatus(302);
        } catch (StripeException se) {
            se.printStackTrace();
        }
    }

    @GetMapping("/payment/customerportal")
    public ResponseEntity<?> manage(HttpServletRequest request) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return null;

        try {
            final String customerID = user.getStripeid();
            final String customerPortalURL = paymentUtil.customerPortal(customerID);
            System.out.println(customerPortalURL);

            HashMap<String, String> map = new HashMap<>();

            map.put("customerportalurl", customerPortalURL);

            return ResponseEntity.ok().body(JsonHelper.toJSON(map));

        } catch (StripeException se) {
            System.err.println(se.getMessage());
        }
        return null;
    }

    @PostMapping("/payment/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String signatureHeader) {
        try {
            webhookUtil.handleHook(payload, signatureHeader);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
