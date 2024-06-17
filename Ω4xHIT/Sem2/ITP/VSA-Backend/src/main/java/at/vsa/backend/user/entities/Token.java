package at.vsa.backend.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Data
@Entity
@Table(name = "token")
public class Token {

    @Id
    private String email;
    private String token;
    private LocalDateTime creationTime;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
    }

}
