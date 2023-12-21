package services;

import model.Company;
import services.db.CompanyDBService;

public class CompanyService {
    public String saveNewCompany(Company company){
        CompanyDBService companyDBService = new CompanyDBService();
        HashService hashService = new HashService();
        if (isLoginUnique(company.getLogin())) {
            company.setPassword(hashService.createHash(company.getPassword()));
            companyDBService.registrationCompany(company);
            LoginService loginService = new LoginService();
            String session = loginService.createSession(company.getLogin());
            return session;
        } else {
            return "Логин уже используется.";
        }
    }

    public boolean isLoginUnique(String login) {
        CompanyDBService companyDBService = new CompanyDBService();
        return companyDBService.isLoginUnique(login);
    }


}
