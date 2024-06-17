package at.vsa.backend.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "linkToken")
public class LinkToken {
    @Id
    private String token;

    private String originalemail;

    private String contact;

    private LocalDateTime creation_time;

    @PrePersist
    protected void onCreate() {
        creation_time = LocalDateTime.now();
    }

}
