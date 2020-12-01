package pl.edu.pjwst.jaz;

import org.springframework.stereotype.Component;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private Map<String, User> users;
    private String username;

    public UserService() {

        users = new HashMap<String, User>();

        User firstUser = new User("user","Robert", "Brown","qwerty", Collections.singleton("user"));
        users.put("user",firstUser);
        User secondUser = new User("admin","Admin", "Admin", "Admin", Collections.singleton("admin"));
        users.put("admin",secondUser);
    }

    public List<User> list() {
        return new ArrayList<User>(users.values());
    }



    public User register(String username, User user) {

        users.put(username, user);
        return user;
    }

    public User getUsername(String username) {

        return users.get(username);
    }
    public String getPassword() {

        return users.get(username).getPassword();
    }
}
