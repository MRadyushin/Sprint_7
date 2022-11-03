package models;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private String login;

    private String password;

    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Courier getRandomCourier() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "password",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getRandomCourierWithoutLogin() {
        return new Courier(
                "",
                "password",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getRandomCourierWithoutPassword() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "",
                RandomStringUtils.randomAlphabetic(10)
        );
    }

    public static Courier getRandomCourierWithoutFirstName() {
        return new Courier(
                RandomStringUtils.randomAlphanumeric(10),
                "password",
                ""
        );
    }

    public Courier() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
