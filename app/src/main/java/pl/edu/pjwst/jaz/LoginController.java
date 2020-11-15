package pl.edu.pjwst.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private final AuthenticationService authenticationService;
    private final UserService userService;


    public LoginController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        //log in
      var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());


      if (!isLogged) {
          throw new UnauthorizedException();
      } else {
          return userService.getUsername(loginRequest.getUsername()).getFirstName();
      }
    }
   @GetMapping("/login")
   public List<User> list() {
       return userService.list();
    }
}
