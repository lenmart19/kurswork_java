package services.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDBService {

    public String getCompanyPassByLogin(String login){
        String password = null;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "select с.\"password\" from company с where с.login = '" + login + "' ";
        ResultSet resultSet = dataBaseService.select(sql);
        try {
            resultSet.next();
            password = resultSet.getString("password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return password;
    }

    public void createSession(String login, String session) {
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "INSERT INTO sessions  (login, session)\n" +
                "VALUES ('" + login + "', '" + session + "')";
        dataBaseService.insert(sql);

    }

    public void cleanSession(String login){
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "delete from sessions s where s.login = '" + login + "'";
        dataBaseService.delete(sql);
    }

    public String getUserLoginBySession(String session){
        String login = null;
        DataBaseService dataBaseService = new DataBaseService();
        String sql = "select s.login from sessions s where s.\"session\" = '" + session + "'";
        ResultSet resultSet = dataBaseService.select(sql);
        try {
            resultSet.next();
            login = resultSet.getString("login");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return login;
    }
}
