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

public class CreateTripServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getSession().getAttribute("session") != null) {
            String session = (String) request.getSession().getAttribute("session");
            request.setAttribute("session", session);
            request.getRequestDispatcher("/pages/create_trip.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/company_actions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("createTripButton".equals(buttonType)) {
                if (request.getSession().getAttribute("session") != null) {
                    String session = (String) request.getSession().getAttribute("session");
                    response.addHeader("session",session);
                    String tripName = request.getParameter("trip_name");
                    String time_from = request.getParameter("time_from");
                    String time_to = request.getParameter("time_to");
                    String bus = request.getParameter("bus");
                    if (tripName == null || tripName.trim().isEmpty() || time_from == null || time_from.trim().isEmpty() || time_to == null || time_to.trim().isEmpty() || bus == null || bus.trim().isEmpty()){
                        request.setAttribute("errorMessage", "Ошибка создания рейса! Все поля должны быть заполены.");
                        request.getRequestDispatcher("/pages/create_trip.jsp").forward(request, response);
                        return;
                    }
                    LoginService loginService = new LoginService();
                    String companyLogin = loginService.getLoginBySession(session);
                    Company company = new Company();
                    company.setLogin(companyLogin);
                    Trip trip = new Trip();
                    trip.setTrip_name(tripName);
                    trip.setTime_from(time_from);
                    trip.setTime_to(time_to);
                    trip.setBus(bus);
                    trip.setCompany(company);
                    CompanyDBService companyDBService = new CompanyDBService();
                    boolean success = companyDBService.createTrip(company, trip);
                    if (success) {
                        request.setAttribute("successMessage", "Рейс создан.");
                    } else {
                        request.setAttribute("errorMessage", "Ошибка создания рейса!");
                    }
                    request.getRequestDispatcher("/pages/create_trip.jsp").forward(request, response);
                }
            } else if ("companyButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company");
            } else {
                response.getWriter().println("Error of button type!");
            }

        }
    }
}

