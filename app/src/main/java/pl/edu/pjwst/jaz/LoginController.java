package pl.edu.pjwst.jaz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class LoginController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserSession userSession;


    public LoginController(AuthenticationService authenticationService, UserService userService, UserSession userSession) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userSession = userSession;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        //log in
     try {


         var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());


         if (!isLogged) {
             throw new UnauthorizedException();
         } else {

             return "U successfully logged in as " + loginRequest.getUsername();
             //response.getWriter().write("U already Logged in !");
         }
     } catch (NullPointerException e) {
         throw new ResponseStatusException(
                 HttpStatus.BAD_REQUEST, "User Not Found", e);
     }
    }

}
