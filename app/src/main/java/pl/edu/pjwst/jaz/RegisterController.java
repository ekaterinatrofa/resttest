package pl.edu.pjwst.jaz;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwst.jaz.model.UserEntity;
import pl.edu.pjwst.jaz.repository.UserEntityService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class RegisterController {
    private final UserService userService;
    private final UserEntityService userEntityService;



    public RegisterController(UserService userService, UserEntityService userEntityService) {
        this.userService = userService;

        this.userEntityService = userEntityService;
    }

    @Transactional
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        //написать

        return userService.register(user);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/listUsers")
//    public String sayWord() {
//
//
//        return "Hello there!";
//    }



    public List<UserEntity> hello() {
        return userEntityService.print();
    }

}
