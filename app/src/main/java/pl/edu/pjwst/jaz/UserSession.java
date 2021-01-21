package pl.edu.pjwst.jaz;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {

    private boolean loggedIn;

    public UserSession() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {

        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {

        this.loggedIn = loggedIn;
    }

}
