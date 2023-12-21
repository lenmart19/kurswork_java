package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PassengerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/passenger.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("showPassengerTripsButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/show_passenger_trips");
            } else if ("pass_in_tripButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/pass_in_trip");
            } else if ("deletePass_in_tripButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/delete_pass_in_trip");
            } else if ("exitButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                response.getWriter().println("Неопределенный тип кнопки");
            }
        }
    }
}
