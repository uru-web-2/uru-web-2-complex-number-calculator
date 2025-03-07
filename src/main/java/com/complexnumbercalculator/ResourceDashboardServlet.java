package com.complexnumbercalculator;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/dashboard/*"})
public class ResourceDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/sign-in");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Dashboard.jsp");
        dispatcher.forward(request, response);
    }
}