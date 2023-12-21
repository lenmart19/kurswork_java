package servlets;
import model.Trip;
import services.db.PassengerDBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowPassengerTripsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PassengerDBService passengerDBService = new PassengerDBService();
        List<Trip> trips = passengerDBService.showPassengerTrips();
        if (!trips.isEmpty()) {
            request.setAttribute("trips", trips);
            request.getRequestDispatcher("/pages/show_passenger_trips.jsp").forward(request, response);
        } else {
            request.setAttribute("trips", trips);
            request.setAttribute("errorText", "Нет доступных билетов для бронирования.");
            request.getRequestDispatcher("/pages/show_passenger_trips.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("passengerButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/passenger");
            } else {
                response.getWriter().println("Неопределенный тип кнопки");
            }
        }
    }
}

