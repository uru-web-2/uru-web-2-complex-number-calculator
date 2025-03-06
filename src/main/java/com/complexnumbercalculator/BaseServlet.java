package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jshell.Snippet;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

// Base servlet class that contains common methods for handling JSON data
public class BaseServlet extends HttpServlet {
    protected StringBuilder readJSONData(HttpServletRequest request) throws IOException {
        // Read JSON data from request body
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        return jsonBuffer;
    }

    // Write JSON data to the response
    protected void writeJSONData(HttpServletResponse response, String status, String data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":\"" + status + "\",\"data\":" + data + "}");
        out.flush();
    }

    // Write JSON data to the response
    protected void writeJSONData(HttpServletResponse response, String status, JSONObject data) throws IOException {
        writeJSONData(response, status, data.toString());
    }

    // Write JSON data to the success response
    protected void writeSuccessJSONData(HttpServletResponse response, JSONObject data) throws IOException {
        writeJSONData(response, "success", data);
    }

    // Write JSON data to the fail response
    protected void writeFailJSONData(HttpServletResponse response, String data) throws IOException {
        writeJSONData(response, "fail", data);
    }

    // Write JSON data to the fail response
    protected void writeFailJSONData(HttpServletResponse response, JSONObject data) throws IOException {
        writeJSONData(response, "fail", data);
    }

    // Write JSON data to the fail response for a given field
    protected void writeFailJSONData(HttpServletResponse response, String field, String message) throws IOException {
        writeFailJSONData(response, "{\"" + field + "\":\"" + message + "\"}");
    }

    // Check if the JSON data is valid
    protected boolean isValidJSONData(StringBuilder jsonBuffer) {
        return jsonBuffer.length() != 0;
    }

    // Handle JSON data reading from the request
    protected StringBuilder handleReadJSONData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            // Read the JSON data
            StringBuilder jsonBuffer = this.readJSONData(request);

            // Check if the JSON data is valid
            if (!isValidJSONData(jsonBuffer)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writeFailJSONData(response, "{\"error\":\"Invalid JSON data\"}");
            }

            return jsonBuffer;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeFailJSONData(response, "{\"error\":\"Invalid JSON data\"}");
        }

        return null;
    }

    // Handle invalid double value in JSON data
    protected void handleInvalidDoubleValue(String key, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writeFailJSONData(response, "{\"error\":\"Invalid " + key + ". Must be a number\"}");
    }

    // Handle invalid string value in JSON data
    protected void handleInvalidStringValue(String key, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writeFailJSONData(response, "{\"error\":\"Invalid " + key + ". Must be a string\"}");
    }
}
