package services;

import services.db.LoginDBService;

import java.util.Date;

public class LoginService {

    public Boolean companyAuth(String login, String pass){
        HashService hashService = new HashService();
        String passHash = hashService.createHash(pass);
        LoginDBService loginDBService = new LoginDBService();
        String getPass = loginDBService.getCompanyPassByLogin(login);
        if(getPass != null && passHash.equals(getPass)){
            return true;
        }
        return false;
    }

    public String createSession(String login){
        HashService hashService = new HashService();
        String session = hashService.createHash(login + new Date());
        LoginDBService loginDBService = new LoginDBService();
        loginDBService.createSession(login, session);
        return session;
    }

    public String getLoginBySession(String session){
        LoginDBService loginDBService = new LoginDBService();
        return  loginDBService.getUserLoginBySession(session);
    }

    public void logOut(String login){
        LoginDBService loginDBService = new LoginDBService();
        loginDBService.cleanSession(login);
    }

}
