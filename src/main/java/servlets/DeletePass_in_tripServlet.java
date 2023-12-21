package servlets;
import model.Trip;
import model.Passenger;
import services.db.PassengerDBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeletePass_in_tripServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PassengerDBService passengerDBService = new PassengerDBService();
        List<Trip> trips = passengerDBService.showUpcomingPassengerTrips();
        request.setAttribute("trips", trips);
        request.getRequestDispatcher("/pages/delete_pass_in_trip.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("deletePass_in_tripButton".equals(buttonType)) {
                String selectedTripId = request.getParameter("trip_id");
                String email = request.getParameter("email");
                if (email == null || email.trim().isEmpty()) {
                    request.setAttribute("errorMessage", "Ошибка бронирования! Проверьте корректность ввеенной электронной почты.");
                    request.getRequestDispatcher("/pages/delete_pass_in_trip.jsp").forward(request, response);
                    return;
                }
                Passenger passenger = new Passenger();
                passenger.setEmail(email);
                Trip trip = new Trip();
                trip.setId_t(Integer.parseInt(selectedTripId));
                PassengerDBService passengerDBService = new PassengerDBService();


                boolean checkPass_in_trip = passengerDBService.isPass_in_tripExist(email, Integer.parseInt(selectedTripId));
                if (checkPass_in_trip) {
                    passengerDBService.deletePass_in_trip(email, Integer.parseInt(selectedTripId));
                    request.setAttribute("successMessage", "Бронирование отменено.");
                } else {
                    request.setAttribute("errorMessage", "Ошибка отмены бронирования! Вы не бронировали этот билет.");
                }
                request.getRequestDispatcher("/pages/delete_pass_in_trip.jsp").forward(request, response);
            } else if ("passengerButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/passenger");
            } else {
                response.getWriter().println("Error of button type!");
            }

        }
    }
}

