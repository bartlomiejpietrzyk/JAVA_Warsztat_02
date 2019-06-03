package pl.coderslab.plain;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private int userGroupId;
    private String name;
    private String password; //zahashowane
    private String email; //unikatowy

    public User() {}

    public User(int id, int userGroupId) {
        this.id = id;
        this.userGroupId = userGroupId;
    }

    public User(int id, String name, String email, int userGroupId) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String password, int userGroupId) {
        this.name = name;
        this.email = email;
        this.hashPassword(password);
        this.userGroupId = userGroupId;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", user_group='" + userGroupId + '\'' +

                '}';
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }
}
