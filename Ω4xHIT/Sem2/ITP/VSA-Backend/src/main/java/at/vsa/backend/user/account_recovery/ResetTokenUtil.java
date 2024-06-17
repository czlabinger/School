package at.vsa.backend.user.account_recovery;

import at.vsa.backend.email.ResetTokenRepository;
import at.vsa.backend.user.entities.Token;
import at.vsa.backend.user.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ResetTokenUtil {

    private final ResetTokenRepository resetTokenRepository;

    //Methode fragt in der Datenbank ab, ob der übergebene Token mit der email vorhanden ist
    public boolean isValidResetTokenForUser(final User user, final String token) {

        Optional<Token> tokenOptional = resetTokenRepository.findByEmailAndToken(user.getEmail(), token);
        if (!tokenOptional.isPresent()) return false;

        Token tmpToken = tokenOptional.get();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime tokenTime = tmpToken.getCreationTime();
        long difference = ChronoUnit.MINUTES.between(tokenTime,currentTime);

        if (difference > 15){
            resetTokenRepository.delete(tmpToken);
            return false;
        }

        return true;
    }

    //Methode erstellt Token in der Datenbank mit Username, Token und Zeit der Erstellung (automatisch in TokenEntity)
    public String createResetTokenForUser(final User user) {

        Token token = null;
        final String uuid = String.valueOf(UUID.randomUUID());

        Optional<Token> tokenOptional = resetTokenRepository.findByEmail(user.getEmail());
        if (tokenOptional.isPresent()) {
            token = tokenOptional.get();
        }else {
            token = new Token();
            token.setEmail(user.getEmail());
        }

        token.setToken(uuid);
        token.setCreationTime(LocalDateTime.now());

        resetTokenRepository.save(token);

        return uuid;
    }

    @Transactional
    public boolean deleteResetTokenForUser(final User user) {

        Optional<Token> tokenOptional = resetTokenRepository.findByEmail(user.getEmail());
        if (!tokenOptional.isPresent()) return false;

        resetTokenRepository.deleteByEmail(user.getEmail());

        return true;
    }
}
