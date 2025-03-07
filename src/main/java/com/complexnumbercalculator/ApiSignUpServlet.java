package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.BiFunction;

// Servlet that handles the sign-up of a user
@WebServlet(urlPatterns = {"/api/sign-up"})
public class ApiSignUpServlet extends ApiSessionBaseServlet {
    // Sign up a user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the JSON data from the request
        StringBuilder jsonBuffer = handleReadJSONData(request, response);
        if (jsonBuffer == null) {
            return;
        }

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());

        // Get the first name of the user
        String firstName = getJSONFirstName(jsonObject, response);
        if (firstName == null) {
            return;
        }

        // Get the last name of the user
        String lastName = getJSONLastName(jsonObject, response);
        if (lastName == null) {
            return;
        }

        // Get the username of the user
        String username = getJSONUsername(jsonObject, response);
        if (username == null) {
            return;
        }

        // Get the email of the user
        String email = getJSONEmail(jsonObject, response);
        if (email == null) {
            return;
        }

        // Get the password of the user
        String password = getJSONPassword(jsonObject, response);
        if (password == null) {
            return;
        }

        // Check if the user already exists
        if (users.containsKey(username)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeFailJSONData(response, "username","Username already exists");
            return;
        }

        // Add the user to the users HashMap
        User user = new User(firstName, lastName, username, email, password);
        users.put(username, user);

        // Write the result as a JSON object
        writeSuccessJSONData(response, null);
    }
}
