package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;

// Servlet that handles the sign in of a user
@WebServlet(urlPatterns = {"/api/sign-in"})
public class ApiSignInServlet extends ApiSessionBaseServlet{
     // Sign in a user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the JSON data from the request
        StringBuilder jsonBuffer = handleReadJSONData(request, response);
        if (jsonBuffer == null) {
            return;
        }

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());

        // Get the username of the user
        String username = getJSONUsername(jsonObject, response);
        if (username == null) {
            return;
        }

        // Get the password of the user
        String password = getJSONPassword(jsonObject, response);
        if (password == null) {
            return;
        }

        // Check if the user exists
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                // Create a session for the user
                request.getSession().setAttribute("username", username);

                // Write the result as a JSON object
                writeSuccessJSONData(response, null);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeFailJSONData(response, "password","Invalid password");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeFailJSONData(response, "username","Invalid username");
        }
    }
}
