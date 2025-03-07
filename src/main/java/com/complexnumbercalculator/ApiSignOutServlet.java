package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet that handles the sign out of a user
@WebServlet(urlPatterns = {"/api/sign-out"})
public class ApiSignOutServlet extends ApiSessionBaseServlet {
    // Sign out a user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Invalidate the session
        request.getSession().invalidate();

        // Write the result as a JSON object
        writeSuccessJSONData(response, null);
    }
}
