package pl.edu.pjwst.jaz;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        //написать

        return userService.register(user.getUsername(),user);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/listUsers")
    public List<User> list() {
        return userService.list();
    }

}
