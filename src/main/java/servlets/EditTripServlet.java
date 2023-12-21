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

public class EditTripServlet extends HttpServlet{
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
            List<Trip> trips = companyDBService.showUpcomingCompanyTrips(company);
            request.setAttribute("trips", trips);
            request.getRequestDispatcher("/pages/edit_trip.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/company_actions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("editTripButton".equals(buttonType)) {
                if (request.getSession().getAttribute("session") != null) {
                    String session = (String) request.getSession().getAttribute("session");
                    response.addHeader("session",session);
                    String selectedTripId = request.getParameter("trip_id");
                    String time_from = request.getParameter("time_from");
                    String time_to = request.getParameter("time_to");
                    String bus = request.getParameter("bus");
                    if (time_from == null || time_from.trim().isEmpty() || time_to == null || time_to.trim().isEmpty() || bus == null || bus.trim().isEmpty()){
                        request.setAttribute("errorMessage", "Ошибка изменения рейса! Все поля должны быть заполены.");
                        request.getRequestDispatcher("/pages/edit_trip.jsp").forward(request, response);
                        return;
                    }
                    LoginService loginService = new LoginService();
                    String companyLogin = loginService.getLoginBySession(session);
                    Company company = new Company();
                    company.setLogin(companyLogin);
                    Trip trip = new Trip();
                    trip.setId_t(Integer.parseInt(selectedTripId));
                    trip.setTime_from(time_from);
                    trip.setTime_to(time_to);
                    trip.setBus(bus);
                    CompanyDBService companyDBService = new CompanyDBService();
                    boolean success = companyDBService.editTrip(company, trip);
                    if (success) {
                        request.setAttribute("successMessage", "Рейс изменен.");
                    } else {
                        request.setAttribute("errorMessage", "Ошибка изменения рейса!");
                    }
                    request.getRequestDispatcher("/pages/edit_trip.jsp").forward(request, response);
                }
            } else if ("companyButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company");
            } else {
                response.getWriter().println("Error of button type!");
            }
        }
    }
}

