package at.vsa.backend.user.authentication.protocols.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String name;
    private String birthdate; //yyyy-mm-dd
    private String password;
    private String country;

}
