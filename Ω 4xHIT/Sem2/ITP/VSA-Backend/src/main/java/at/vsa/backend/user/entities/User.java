package at.vsa.backend.user.entities;

import at.vsa.backend.user.authentication.config.Country;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String name;

    private LocalDate birthdate;

    @Setter
    private LocalDateTime lastverification;

    @Setter
    private String wakeuptoken;

    @Setter
    @Enumerated(EnumType.STRING)
    private Country country;

    @Setter
    private int subversion;
    
    @Setter
    private int verificationinterval;

    @Setter
    private String stripeid;

    @Setter
    private int wakeupcount;

    @Setter
    private int wronglogin;

    @Setter
    private boolean locked;

    @Setter
    private boolean dead;

    //TODO: rethink whether this belongs here or to userService
    public static final String pepper = "3prs%GFh86x1B*EKnO@367JSYz^VPv@ztqh5pXNs";

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
