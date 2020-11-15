package pl.edu.pjwst.jaz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserService userService;


    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        logger.warn(String.valueOf(userService.uu()));
        if (userService.getUsername(username).getPassword().equals(password)) {
        //logger.warn(userService.getUsername(username).getPassword());
          return true;
       }


        return false;
    }
}
