package at.vsa.backend.payment;

import at.vsa.backend.BackendApplication;
import at.vsa.backend.user.UserRepository;
import at.vsa.backend.user.authentication.config.Country;
import at.vsa.backend.user.entities.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PriceListParams;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentUtil {
    UserRepository userRepository;

    public PaymentUtil(UserRepository userRepository) {
        Stripe.apiKey = BackendApplication.STRIPE_KEY;
        this.userRepository = userRepository;
    }

    public String createCustomer(User user) throws IllegalArgumentException {
        if (user.getStripeid() == null) {

            CustomerCreateParams.Builder params = CustomerCreateParams.builder()
                    .setName(user.getName())
                    .setEmail(user.getEmail());

            switch (user.getCountry()) {
                case Country.AUSTRIA:
                    params.addPreferredLocale("de-AT");
                    break;
                case Country.GERMANY:
                    params.addPreferredLocale("de-DE");
                    break;
                default:
                    params.addPreferredLocale("en-US");
                    break;
            }

            try {
                CustomerCreateParams customerCreateParams = params.build();
                Customer customer = Customer.create(customerCreateParams);
                createDefaultSubscription(customer.getId());

                user.setStripeid(customer.getId());
                userRepository.save(user);

                return customer.getId();
            }catch (StripeException se) {
                System.err.println(se.getMessage());
            }

        } else {
            throw new IllegalArgumentException("Customer already exists.");
        }
        return null;
    }

    public void createDefaultSubscription(String userId) throws StripeException {
        PriceListParams priceParams = PriceListParams.builder().addLookupKey("default").build();
        PriceCollection price = Price.list(priceParams);

        SubscriptionCreateParams scp = SubscriptionCreateParams.builder()
                .setCustomer(userId)
                .addItem(SubscriptionCreateParams.Item.builder().setPrice(price.getData().getFirst().getId()).build())
                .build();
        Subscription.create(scp);
    }

    public String checkoutSession(String plan, String period, String customerID) throws StripeException {
        PriceListParams priceParams = PriceListParams.builder().addLookupKey(plan.toLowerCase() + "_" + period.toLowerCase()).build();
        PriceCollection prices = Price.list(priceParams);

        com.stripe.param.checkout.SessionCreateParams params = SessionCreateParams.builder()
                .addLineItem(SessionCreateParams.LineItem.builder().setPrice(prices.getData().getFirst().getId()).setQuantity(1L).build())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setCustomer(customerID)
                //TODO implement correct URL
                .setSuccessUrl("http://localhost:3000/success.html")
                .setCancelUrl("http://localhost:3000/cancel.html")
                .build();
        Session session = Session.create(params);
        return session.getUrl();
    }

    public String customerPortal(String customerID) throws StripeException {
        com.stripe.param.billingportal.SessionCreateParams params =
                com.stripe.param.billingportal.SessionCreateParams.builder()
                        .setCustomer(customerID)
                        //TODO implement correct URL
                        .setReturnUrl("http://localhost:3000/success.html")
                        .setConfiguration("bpc_1OuVT4HVfkWq99uxq9dUxqCp")
                        .build();
        com.stripe.model.billingportal.Session session = com.stripe.model.billingportal.Session.create(params);
        return session.getUrl();
    }
}
