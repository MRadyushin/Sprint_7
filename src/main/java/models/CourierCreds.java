package models;

public class CourierCreds {

    private String login;

    private String password;

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCreds() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}