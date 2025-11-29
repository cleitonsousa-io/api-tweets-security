package fcss.dev.security.controller;

import fcss.dev.security.controller.dto.LoginRequestDTO;
import fcss.dev.security.controller.dto.LoginResponseDTO;
import fcss.dev.security.entities.Role;
import fcss.dev.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        var user = authService.authenticate(loginRequestDTO);

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
