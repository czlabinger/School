package at.vsa.backend.user.account_recovery;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ResetTokenUtil {
    
    //Methode fragt in der Datenbank ab, ob der übergebene Token gültig ist
    public boolean isValidToken(String username, String token) {
        //Verbindung zur Datenbank herstellen
        //if(checkTokenDatabase(username) == token)
        return true;
    }

    //Methode erstellt Token in der Datenbank mit Username, Token und Zeit der Erstellung
    public String createToken(String usernameOrEmail) {
        //Tabel für Token
        //if(getUserByUsernameOrEmail()==userOrEmail)
        UUID uuid = UUID.randomUUID();
        LocalDateTime ldt = LocalDateTime.now();
        //saveToDatabase(username, uuid, ldt);
        //
        return uuid.toString();
    }
}
