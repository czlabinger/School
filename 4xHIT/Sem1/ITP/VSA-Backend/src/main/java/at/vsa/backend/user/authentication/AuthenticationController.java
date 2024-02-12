package at.vsa.backend.user.authentication;

import at.vsa.backend.user.authentication.protocols.request.AuthenticationRequest;
import at.vsa.backend.user.authentication.protocols.response.AuthenticationResponse;
import at.vsa.backend.user.authentication.config.AuthenticationService;
import at.vsa.backend.user.authentication.protocols.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
