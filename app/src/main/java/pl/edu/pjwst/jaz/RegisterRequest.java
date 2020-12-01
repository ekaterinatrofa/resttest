package pl.edu.pjwst.jaz;

public class RegisterRequest {
    private String username;
    private  String password;
    private String firstName;
    private String lastName;

    private String role;

    public User user;


    public RegisterRequest(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(String firstName, String lastName, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }
}
