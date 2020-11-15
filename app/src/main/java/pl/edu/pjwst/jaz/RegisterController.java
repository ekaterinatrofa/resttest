package pl.edu.pjwst.jaz;

import org.springframework.web.bind.annotation.*;

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
        return userService.register(user);
    }
    @GetMapping("/register")
    public List<User> list() {
        return userService.list();
    }
}
