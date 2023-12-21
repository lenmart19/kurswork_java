package servlets;
import model.Company;
import services.LoginService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyLoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/company_login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("companyButton".equals(buttonType)) {
                request.getServletContext();
                Company company = new Company(request);
                LoginService loginService = new LoginService();
                if(loginService.companyAuth(company.getLogin(), company.getPassword())){
                    loginService.logOut(company.getLogin());
                    String session = loginService.createSession(company.getLogin());
                    request.getSession().setAttribute("session", session);
                    response.addHeader("session",session);
                    response.sendRedirect(request.getContextPath() + "/company");
                } else {
                    request.setAttribute("errorText", "Ошибка в логине или пароле!");
                    request.getRequestDispatcher("/pages/company_login.jsp").forward(request, response);
                    super.doPost(request, response);
                }
            } else if ("companyActionsButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company_actions");
            } else {
                response.getWriter().println("Error of button type!");
            }
        }
    }
}
