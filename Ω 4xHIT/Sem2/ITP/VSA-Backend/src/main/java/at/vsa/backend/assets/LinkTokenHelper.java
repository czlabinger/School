package at.vsa.backend.assets;

import at.vsa.backend.user.entities.LinkToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
public class LinkTokenHelper {

    private final LinkTokenRepository linkTokenRepository;

    public String createVsaLink(String originalEmail, String contact) {
        UUID uuid = UUID.randomUUID();
        LinkToken lt = new LinkToken();
        lt.setOriginalemail(originalEmail);
        lt.setContact(contact);
        lt.setToken(String.valueOf(uuid));
        lt.setCreation_time(LocalDateTime.now());
        linkTokenRepository.save(lt);
        return "https://www.localhost:8080/assets/contactList?token=" + uuid; //TODO: Change to actual domain
        //TODO: Additional Reference: actual domain will be a static string in the main file or in a config json
    }

    public LinkToken getLinkToken(String token) {
        return linkTokenRepository.findEmailByToken(token).orElseThrow(() -> new UsernameNotFoundException("No entry for LinkToken: " + token));
    }
}
