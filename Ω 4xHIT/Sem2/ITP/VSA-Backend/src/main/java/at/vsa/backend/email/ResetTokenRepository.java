package at.vsa.backend.email;

import at.vsa.backend.user.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ResetTokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByEmailAndToken(String email, String token);
    Optional<Token> findByEmail(String email);

    void deleteByEmail(String email);
}
