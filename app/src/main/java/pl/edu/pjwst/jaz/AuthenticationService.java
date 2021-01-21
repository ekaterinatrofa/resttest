package pl.edu.pjwst.jaz;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserService userService;
    private final UserSession userSession;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthenticationService(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    public boolean login(String username, String password) {
       //logger.warn(String.valueOf(userService.getRole(username)));
       // logger.warn(userService.getUsername(username).getPassword());
        //logger.warn(String.valueOf(passwordEncoder.matches(password,userService.getUsername(username).getPassword())));
        if (passwordEncoder.matches(password,userService.getUsername(username).getPassword())) {
            logger.warn(userService.getUsername(username).getPassword());
            userSession.setLoggedIn(true);

            SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userService.getUsername(username)));
            return true;

        }


        return false;
    }
}
