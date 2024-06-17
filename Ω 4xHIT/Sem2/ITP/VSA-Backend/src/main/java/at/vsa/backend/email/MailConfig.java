package at.vsa.backend.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource("classpath:email.properties")
@Configuration
public class MailConfig {


    @Value("${email.server.host}")
    private String host;

    @Value("${email.server.port}")
    private int port;

    @Value("${email.server.username}")
    private String username;

    @Value("${email.server.password}")
    private String password;

    @Value("${email.server.properties.mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${email.server.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnabled;

    @Value("${email.server.properties.mail.smtp.ssl.enable}")
    private boolean sslEnabled;

    @Value("${email.server.debug}")
    private boolean debugging;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", starttlsEnabled);
        props.put("mail.smtp.ssl.enable", sslEnabled);
        props.put("mail.debug", debugging);

        return mailSender;
    }
}