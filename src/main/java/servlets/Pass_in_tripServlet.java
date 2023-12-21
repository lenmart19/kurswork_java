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

public class Pass_in_tripServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PassengerDBService passengerDBService = new PassengerDBService();
        List<Trip> trips = passengerDBService.showUpcomingPassengerTrips();
        request.setAttribute("trips", trips);
        request.getRequestDispatcher("/pages/pass_in_trip.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("pass_in_tripButton".equals(buttonType)) {
                String selectedTripId = request.getParameter("trip_id");
                String passenger_name = request.getParameter("passenger_name");
                String email = request.getParameter("email");
                if (passenger_name == null || passenger_name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
                    request.setAttribute("errorMessage", "Ошибка бронирования! Необходимо ввести имя и электронную почту.");
                    request.getRequestDispatcher("/pages/pass_in_trip.jsp").forward(request, response);
                    return;
                }
                Passenger passenger = new Passenger();
                passenger.setPassenger_name(passenger_name);
                passenger.setEmail(email);
                Trip trip = new Trip();
                trip.setId_t(Integer.parseInt(selectedTripId));
                PassengerDBService passengerDBService = new PassengerDBService();
                boolean checkUser = passengerDBService.isUserExist(email);
                int passengerId;
                if (checkUser) {
                    passengerDBService.editPassengerName(passenger);
                } else {
                    passengerDBService.createPassenger(passenger);
                }
                Passenger newPassenger = passengerDBService.getPassengerByEmail(passenger.getEmail());
                passengerId = newPassenger.getId_p();
                boolean success = passengerDBService.createPass_in_trip(trip.getId_t(), passengerId);
                if (success) {
                    request.setAttribute("successMessage", "Билет забронирован.");
                } else {
                    request.setAttribute("errorMessage", "Ошибка бронирования.");
                }
                request.getRequestDispatcher("/pages/pass_in_trip.jsp").forward(request, response);
            } else if ("passengerButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/passenger");
            } else {
                response.getWriter().println("Error of button type!");
            }
        }
    }
}

