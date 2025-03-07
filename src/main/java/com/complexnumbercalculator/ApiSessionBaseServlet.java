package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiFunction;

// Servlet that handles the operations with two polar complex numbers
public class ApiSessionBaseServlet extends BaseServlet {
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
}
