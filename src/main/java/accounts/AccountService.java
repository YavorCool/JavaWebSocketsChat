package accounts;

import dbService.DBService;
import dbService.dataSets.UserDataSet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qwe on 31.05.2016.
 */
public class AccountService {
    private final Map <String, UserDataSet> sessionIdToProfile; //users online;
    private DBService dbService;

    public AccountService(DBService dbServiceImpl) {
        sessionIdToProfile = new HashMap<String, UserDataSet>();
        this.dbService = dbServiceImpl;
    }

    public void addNewUser(UserDataSet userDataSet) throws SQLException {
        dbService.addUser(userDataSet.getLogin(), userDataSet.getPassword());
    }

    public UserDataSet getUserByLogin(String login) {
        return dbService.getUserByName(login);
    }

    public UserDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserDataSet usersDataSet) {
        sessionIdToProfile.put(sessionId, usersDataSet);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }



}
