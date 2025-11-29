package fcss.dev.security.service;

import fcss.dev.security.controller.dto.CreateUserDTO;
import fcss.dev.security.entities.Role;
import fcss.dev.security.entities.User;
import fcss.dev.security.repository.RoleRepository;
import fcss.dev.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(CreateUserDTO dto) {
        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        userRepository.findByUsername(dto.username())
                .ifPresent(u -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists"); });

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
