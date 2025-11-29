package fcss.dev.security.service;

import fcss.dev.security.controller.dto.LoginRequestDTO;
import fcss.dev.security.entities.User;
import fcss.dev.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUsername(loginRequestDTO.username())
                .orElseThrow(() -> new BadCredentialsException("User or Password is invalid!"));

        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("User or Password is invalid!");
        }

        return user;
    }
}
