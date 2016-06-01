package dbService.dataSets;

/**
 * Created by qwe on 31.05.2016.
 */
public class UserDataSet {
    private long id;

    private final String login;
    private String password;

    public UserDataSet() {
        this.login = "";
        this.password = "";
        this.id = -1;
    }

    public UserDataSet(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserDataSet(String login, String password) {
        this.login = login;
        this.password = password;
        this.id = -1;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
