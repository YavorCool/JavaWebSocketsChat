package dbService.jdbc;

import com.mysql.jdbc.Driver;
import dbService.DBService;
import dbService.dataSets.UserDataSet;
import dbService.jdbc.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by qwe on 01.06.2016.
 */
public class DBServiceImplJDBC implements DBService {
    private final Connection connection;

    public DBServiceImplJDBC(Connection connection) {
        this.connection = connection;
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url
                    .append("jdbc:mysql//") //db type
                    .append("127.0.0.1:") //db ip
                    .append("3306/") //db port
                    .append("users?")// db name
                    .append("user=root&") //user
                    .append("password=2134356");//password

            return DriverManager.getConnection(url.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public UserDataSet getUser(long id) throws SQLException {
        return new UserDAO(connection).get(id);
    }

    @Override
    public UserDataSet getUserByName(String name) throws SQLException {
        UserDAO UD = new UserDAO(connection);
        return UD.get(UD.getUserId(name));
    }

    @Override
    public long addUser(String login, String password) throws SQLException {
        UserDAO UD = new UserDAO(connection);
        UD.insertUser(login, password);
        return UD.getUserId(login);
    }
}
