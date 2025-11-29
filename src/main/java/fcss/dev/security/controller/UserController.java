package fcss.dev.security.controller;

import fcss.dev.security.controller.dto.CreateUserDTO;
import fcss.dev.security.entities.User;
import fcss.dev.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO dto){
        userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<User>> listUsers(){
        List<User> users = userService.listUsers();
        return ResponseEntity.ok(users);
    }
}
