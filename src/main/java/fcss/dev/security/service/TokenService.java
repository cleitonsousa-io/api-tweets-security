package fcss.dev.security.service;

import fcss.dev.security.controller.dto.LoginRequestDTO;
import fcss.dev.security.entities.User;
import fcss.dev.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final UserRepository userRepository;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoginCorrect(LoginRequestDTO loginRequestDTO, PasswordEncoder passwordEncoder) {
        User user = userRepository.findByUsername(loginRequestDTO.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(loginRequestDTO.password(), user.getPassword());
    }
}
