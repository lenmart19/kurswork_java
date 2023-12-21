package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyActionsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/pages/company_actions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] buttonTypes = request.getParameterValues("buttonType");
        if (buttonTypes != null && buttonTypes.length > 0) {
            String buttonType = buttonTypes[0];
            if ("loginButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company_login");
            } else if ("registerButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/company_registration");
            } else if ("exitButton".equals(buttonType)) {
                response.sendRedirect(request.getContextPath() + "/index");
            } else {
                response.getWriter().println("Error of button type!");
            }
        }
    }
}

