package at.vsa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {

	public static final String BUCKET_NAME = "vsa-backend-assets";
	public static final String FROM = "noreply@digital-reservoir.de"; //TODO: change email

	//Stripe Test key (rk_test)
	public static final String STRIPE_KEY = "rk_test_51OuV5OHVfkWq99uxQWWQ7Qv0SuLBP2U79OUvFQ6dNWTCOmjvTvQFiVZJuzgQCLDmbhA41swB1Du5td2hGHiIy50T00P97y6rHB";

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}