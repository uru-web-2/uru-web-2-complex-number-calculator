package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiFunction;

// Servlet that handles the operations with two polar complex numbers
public class ApiSessionServlet extends BaseServlet {
    static String FIRST_NAME_KEY = "first_name";
    static String LAST_NAME_KEY = "last_name";
    static String EMAIL_KEY = "email";
    static String USERNAME_KEY = "username";
    static String PASSWORD_KEY = "password";
    static HashMap<String, User> users = new HashMap<>();

    // Get the JSON first name value from a JSONObject
    protected String getJSONFirstName(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getString(FIRST_NAME_KEY);
        } catch (Exception e) {
            handleInvalidStringValue(FIRST_NAME_KEY, response);
        }
        return null;
    }

    // Get the JSON last name value from a JSONObject
    protected String getJSONLastName(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getString(LAST_NAME_KEY);
        } catch (Exception e) {
            handleInvalidStringValue(LAST_NAME_KEY, response);
        }
        return null;
    }

    // Get the JSON username value from a JSONObject
    protected String getJSONUsername(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getString(USERNAME_KEY);
        } catch (Exception e) {
            handleInvalidStringValue(USERNAME_KEY, response);
        }
        return null;
    }

    // Get the JSON email value from a JSONObject
    protected String getJSONEmail(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getString(EMAIL_KEY);
        } catch (Exception e) {
            handleInvalidStringValue(EMAIL_KEY, response);
        }
        return null;
    }

    // Get the JSON password value from a JSONObject
    protected String getJSONPassword(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getString(PASSWORD_KEY);
        } catch (Exception e) {
            handleInvalidStringValue(PASSWORD_KEY, response);
        }
        return null;
    }

    // Sign up a user
    protected void signUp(HttpServletRequest request, HttpServletResponse response, BiFunction<PolarComplexNumber, PolarComplexNumber, RectangularComplexNumber> operator) throws IOException {
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

        // Add the user to the users HashMap
        User user = new User(firstName, lastName, username, email, password);
        users.put(username, user);

        // Write the result as a JSON object
        writeSuccessJSONData(response, null);
    }

    // Sign in a user
    protected void signIn(HttpServletRequest request, HttpServletResponse response, BiFunction<PolarComplexNumber, PolarComplexNumber, RectangularComplexNumber> operator) throws IOException {
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

    // Sign out a user
    protected void signOut(HttpServletRequest request, HttpServletResponse response, BiFunction<PolarComplexNumber, PolarComplexNumber, RectangularComplexNumber> operator) throws IOException {
        // Invalidate the session
        request.getSession().invalidate();

        // Write the result as a JSON object
        writeSuccessJSONData(response, null);
    }
}
