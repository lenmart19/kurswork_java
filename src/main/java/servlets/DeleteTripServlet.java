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

public class DeleteTripServlet extends HttpServlet{
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
            request.getRequestDispatcher("/pages/delete_trip.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/company_actions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("deleteTripButton".equals(buttonType)) {
                if (request.getSession().getAttribute("session") != null) {
                    String session = (String) request.getSession().getAttribute("session");
                    response.addHeader("session",session);
                    String selectedTripId = request.getParameter("trip_id");
                    if (selectedTripId == null || selectedTripId.trim().isEmpty()){
                        request.setAttribute("errorMessage", "Ошибка удаления рейса! Необходимо выбрать рейс.");
                        request.getRequestDispatcher("/pages/delete_trip.jsp").forward(request, response);
                        return;
                    }
                    LoginService loginService = new LoginService();
                    String companyLogin = loginService.getLoginBySession(session);
                    Company company = new Company();
                    company.setLogin(companyLogin);
                    Trip trip = new Trip();
                    trip.setId_t(Integer.parseInt(selectedTripId));
                    CompanyDBService companyDBService = new CompanyDBService();
                    boolean success = companyDBService.deleteTrip(company, trip);
                    if (success) {
                        request.setAttribute("successMessage", "Рейс удален.");
                    } else {
                        request.setAttribute("errorMessage", "Ошибка удаления рейса!.");
                    }
                    request.getRequestDispatcher("/pages/delete_trip.jsp").forward(request, response);
                }
            } else if ("companyButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company");
            } else {
                response.getWriter().println("Error of button type!");
            }

        }
    }
}
