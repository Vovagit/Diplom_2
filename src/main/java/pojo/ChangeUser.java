package pojo;

public class ChangeUser {
    private String name;
    private String email;

    public ChangeUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public ChangeUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
