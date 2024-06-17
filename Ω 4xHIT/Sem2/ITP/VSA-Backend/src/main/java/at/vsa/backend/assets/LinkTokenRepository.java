package at.vsa.backend.assets;

import at.vsa.backend.user.entities.LinkToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkTokenRepository extends JpaRepository<LinkToken, String> {
    Optional<LinkToken> findEmailByToken(String token);
}
