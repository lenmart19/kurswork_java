package servlets;
import model.Company;
import services.LoginService;
import services.CompanyService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyRegistrationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/company_registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("companyButton".equals(buttonType)) {
                Company newCompany = new Company(request);
                if (newCompany.getLogin() == null || newCompany.getLogin().trim().isEmpty() || newCompany.getCompany_name() == null || newCompany.getCompany_name().trim().isEmpty() || newCompany.getPassword() == null || newCompany.getPassword().trim().isEmpty()) {
                    request.setAttribute("errorText", "Ошибка регистрации! Необходимо заполнить все поля.");
                    request.getRequestDispatcher("/pages/company_registration.jsp").forward(request, response);
                    return;
                }
                CompanyService companyService = new CompanyService();
                if (companyService.isLoginUnique(newCompany.getLogin())) {
                    companyService.saveNewCompany(newCompany);
                    LoginService loginService = new LoginService();
                    String session = loginService.createSession(newCompany.getLogin());
                    request.getSession().setAttribute("session", session);
                    response.addHeader("session", session);
                    response.sendRedirect(request.getContextPath() + "/company");
                } else {
                    request.setAttribute("errorText", "Логин уже используется.");
                    request.getRequestDispatcher("/pages/company_registration.jsp").forward(request, response);
                    return;
                }
            } else if ("companyActionsButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company_actions");
            } else {
                response.getWriter().println("Error of button type!");
            }
        }
    }
}
