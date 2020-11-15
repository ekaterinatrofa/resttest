package pl.edu.pjwst.jaz;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private Map<String, User> users;
    private String username;

    public UserService() {

        users = new HashMap<String, User>();

        User firstUser = new User("Robert", "Brown","hghkgkuh", "user");
        users.put("user",firstUser);
        User secondUser = new User("Admin", "Admin", "Admin", "admin");
        users.put("admin",secondUser);
    }

    public List<User> list() {

        return new ArrayList<User>(users.values());
    }

    public User register(User user) {

        users.put(username, user);
        return user;
    }

    public User getUsername(String username) {
        return users.get(username);
    }


    public User update(Long id, User user) {
        users.put(username, user);
        return user;
    }

    public User delete(Long id) {
        return users.remove(id);
    }
}
