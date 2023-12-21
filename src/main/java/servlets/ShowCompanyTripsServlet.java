package servlets;
import model.Trip;
import model.Company;
import services.LoginService;
import services.db.CompanyDBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class ShowCompanyTripsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession().getAttribute("session") != null) {
            String session = (String) request.getSession().getAttribute("session");
            request.setAttribute("session", session);
            LoginService loginService = new LoginService();
            String companyLogin = loginService.getLoginBySession(session);
            Company company = new Company();
            company.setLogin(companyLogin);
            CompanyDBService companyDBService = new CompanyDBService();
            List<Trip> trips = companyDBService.showCompanyTrips(company);
            if (!trips.isEmpty()) {
                request.setAttribute("trips", trips);
                request.getRequestDispatcher("/pages/show_company_trips.jsp").forward(request, response);
            } else {
                request.setAttribute("trips", trips);
                request.getRequestDispatcher("/pages/show_company_trips.jsp").forward(request, response);

            }
        } else {
            response.sendRedirect(request.getContextPath() + "/company_actions");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("companyButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company");
            } else {
                response.getWriter().println("Неопределенный тип кнопки");
            }
        }
    }
}

