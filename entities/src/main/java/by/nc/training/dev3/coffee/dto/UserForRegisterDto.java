package by.nc.training.dev3.coffee.dto;

/**
 * Created by Win on 04.06.2017.
 */
public class UserForRegisterDto {
    private String login;
    private String password;
    private String repeatedPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
