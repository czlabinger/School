package at.vsa.backend.payment;

import at.vsa.backend.email.EmailServiceImpl;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.entities.Role;
import at.vsa.backend.user.entities.User;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WebhookUtil {


    private final String endpointSecret = "whsec_c43bf26bd0c550b319382b0e086ce36b40a78eee3d977e22072ea9f40172299d";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailServiceImpl emailService;

    private final PaymentUtil paymentUtil;

    public WebhookUtil() {
        paymentUtil = new PaymentUtil(userRepository);
    }

    public void handleHook(String payload, String signatureHeader) throws IllegalArgumentException, NotImplementedException, StripeException {
        Event event = null;
        event = Webhook.constructEvent(payload, signatureHeader, endpointSecret);
        System.out.println(event.getType());
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            throw new IllegalArgumentException("Deserialization failed");
        }

        switch (event.getType()) {
            case "customer.subscription.updated":
                Subscription subscription = (Subscription) stripeObject;
                Optional<User> u = userRepository.findByStripeid(subscription.getCustomer());
                if (u.isPresent()) {
                    User user = u.get();
                    String activeSub = subscription.getItems().getData().getFirst().getPlan().getProduct();
                    System.out.println("Product: " + activeSub);
                    String activeSubName = Product.retrieve(activeSub).getName();
                    System.out.println("Product name: " + activeSubName);
                    switch (activeSubName) {
                        case "Standard":
                            user.setRole(Role.BASIC);
                            userRepository.save(user);
                            break;
                        case "Premium":
                            user.setRole(Role.PREMIUM);
                            userRepository.save(user);
                            break;
                        case "Advanced":
                            user.setRole(Role.ADVANCED);
                            userRepository.save(user);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown role: " + activeSubName);
                    }
                    //./stripe products retrieve prod_Px7I2udcDL8Ua8
                }
                break;

            case "invoice.created":
                Invoice invoice = (Invoice) stripeObject;
                String invoiceReceiptMail = invoice.getCustomerEmail();
                String invoiceReceiptName = invoice.getCustomerName();
                String invoiceLink = invoice.getHostedInvoiceUrl();
                emailService.sendInvoice(invoiceReceiptMail, invoiceReceiptName, invoiceLink);
                break;

            case "customer.subscription.deleted":
                Subscription delSub = (Subscription) stripeObject;
                Optional<User> user = userRepository.findByStripeid(delSub.getCustomer());
                if (user.isPresent()) {
                    User tmpU = user.get();
                    tmpU.setRole(Role.BASIC);
                    userRepository.save(tmpU);
                    paymentUtil.createDefaultSubscription(tmpU.getStripeid());
                }
                break;
            default:
                throw new NotImplementedException();
        }
    }
}
