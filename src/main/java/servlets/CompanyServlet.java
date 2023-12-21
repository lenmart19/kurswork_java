package servlets;

import services.db.LoginDBService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("createTripButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/create_trip");
            } else if ("showCompanyTripsButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/show_company_trips");
            } else if ("editTripButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/edit_trip");
            } else if ("deleteTripButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/delete_trip");
            } else if ("exitButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                response.getWriter().println("Неопределенный тип кнопки");
            }
        }
    }
}
