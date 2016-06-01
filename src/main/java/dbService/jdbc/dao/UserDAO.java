package dbService.jdbc.dao;

import dbService.dataSets.UserDataSet;
import dbService.jdbc.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qwe on 01.06.2016.
 */
public class UserDAO {
    private Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserDataSet get(long id) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE id=" + id, resultSet -> {
           resultSet.next();
            return new UserDataSet(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
        });
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("SELECT * FROM users WHERE login = '" + login + "'", result->{
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String login, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users (login, password) VALUES ('" + login + "', '" + password +"')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("DROP TABLE users");
    }
}
