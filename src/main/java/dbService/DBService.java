package dbService;

import dbService.dataSets.UserDataSet;

import java.sql.SQLException;

/**
 * Created by qwe on 31.05.2016.
 */
public interface DBService {
    public UserDataSet getUser(long id) throws SQLException;
    public UserDataSet getUserByName(String name) throws SQLException;
    public long addUser(String login, String password) throws SQLException;
}
