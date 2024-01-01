public class User {
    private String firstName;
    private String lastName;
    private String Password;
    private String Email;
    private String UserName;

    public User(String firstName, String lastName, String password, String email, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        Password = password;
        Email = email;
        UserName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
