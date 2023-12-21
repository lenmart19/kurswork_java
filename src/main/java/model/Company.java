package model;
import lombok.Data;
import javax.servlet.http.HttpServletRequest;

@Data
public class Company {
    private String login;
    private String company_name;
    private String password;

    public Company() {
    }

    public Company(String login, String company_name, String password) {
        this.login = login;
        this.company_name = company_name;
        this.password = password;
    }

    public Company(HttpServletRequest request){
        this.login = request.getParameter("login");
        this.company_name = request.getParameter("company_name");
        this.password = request.getParameter("password");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

