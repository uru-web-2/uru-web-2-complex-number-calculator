package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseServlet extends HttpServlet {
    static String A_MAGNITUDE_KEY = "a_magnitude";
    static String A_ANGLE_KEY = "a_angle";
    static String B_MAGNITUDE_KEY = "b_magnitude";
    static String B_ANGLE_KEY = "b_angle";
    static String A_REAL_KEY = "a_real";
    static String A_IMAGINARY_KEY = "a_imaginary";
    static String B_REAL_KEY = "b_real";
    static String B_IMAGINARY_KEY = "b_imaginary";
    static String RETURN_TYPE_KEY = "return_type";
    static String MAGNITUDE_KEY = "magnitude";
    static String ANGLE_KEY = "angle";
    static String REAL_KEY = "real";
    static String IMAGINARY_KEY = "imaginary";
    static String RETURN_TYPE_RECTANGULAR = "rectangular";
    static String RETURN_TYPE_POLAR = "polar";

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
    protected void writeJSONData(HttpServletResponse response, String data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(data);
        out.flush();
    }

    // Write JSON data to the response
    protected void writeJSONData(HttpServletResponse response, JSONObject data) throws IOException {
        writeJSONData(response, data.toString());
    }

    // Write JSON data to the response
    protected void writeJSONData(HttpServletResponse response, StringBuilder data) throws IOException {
        writeJSONData(response, data.toString());
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
                writeJSONData(response, "{\"error\":\"Invalid JSON data\"}");
            }

            return jsonBuffer;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJSONData(response, "{\"error\":\"Invalid JSON data\"}");
        }

        return null;
    }

    // Handle JSON data writing to the response
    protected void handleWriteJSONData(HttpServletResponse response, PolarComplexNumber polarComplexNumber, String returnType) throws IOException {
        if (returnType.equals(RETURN_TYPE_POLAR)) {
            writeJSONData(response, "{\""+MAGNITUDE_KEY+"\":" + polarComplexNumber.getMagnitude() + ",\""+ANGLE_KEY+"\":" + polarComplexNumber.getAngle() + "}");
        } else {
            RectangularComplexNumber rectangularComplexNumber = polarComplexNumber.toRectangular();
            writeJSONData(response, "{\""+REAL_KEY+"\":" + rectangularComplexNumber.getReal() + ",\""+IMAGINARY_KEY+"\":" + rectangularComplexNumber.getImaginary() + "}");
        }
    }

    // Handle JSON data writing to the response
    protected void handleWriteJSONData(HttpServletResponse response, RectangularComplexNumber rectangularComplexNumber, String returnType) throws IOException {
        if (returnType.equals(RETURN_TYPE_POLAR)) {
            PolarComplexNumber polarComplexNumber = RectangularComplexNumber.toPolar(rectangularComplexNumber);
            writeJSONData(response, "{\""+MAGNITUDE_KEY+"\":" + polarComplexNumber.getMagnitude() + ",\""+ANGLE_KEY+"\":" + polarComplexNumber.getAngle() + "}");
        } else {
            writeJSONData(response, "{\""+REAL_KEY+"\":" + rectangularComplexNumber.getReal() + ",\""+IMAGINARY_KEY+"\":" + rectangularComplexNumber.getImaginary() + "}");
        }
    }

    // Handle invalid double value in JSON data
    protected void handleInvalidDoubleValue(String key, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writeJSONData(response, "{\"error\":\"Invalid " + key + ". Must be a number\"}");
    }

    // Get the JSON magnitude value of the first polar complex number from a JSONObject
    protected Double getJSONAMagnitude(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(A_MAGNITUDE_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(A_MAGNITUDE_KEY, response);
        }
        return null;
    }

    // Get the JSON angle value of the first polar complex number from a JSONObject
    protected Double getJSONAAngle(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(A_ANGLE_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(A_ANGLE_KEY, response);
        }
        return null;
    }

    // Get the JSON magnitude value of the second polar complex number from a JSONObject
    protected Double getJSONBMagnitude(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(B_MAGNITUDE_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(B_MAGNITUDE_KEY, response);
        }
        return null;
    }

    // Get the JSON angle value of the second polar complex number from a JSONObject
    protected Double getJSONBAngle(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(B_ANGLE_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(B_ANGLE_KEY, response);
        }
        return null;
    }

    // Get the JSON real value of the first rectangular complex number from a JSONObject
    protected Double getJSONAReal(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(A_REAL_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(A_REAL_KEY, response);
        }
        return null;
    }

    // Get the JSON imaginary value of the first rectangular complex number from a JSONObject
    protected Double getJSONAImaginary(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(A_IMAGINARY_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(A_IMAGINARY_KEY, response);
        }
        return null;
    }

    // Get the JSON real value of the second rectangular complex number from a JSONObject
    protected Double getJSONBReal(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
            return jsonObject.getDouble(B_REAL_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(B_REAL_KEY, response);
        }
        return null;
    }

    // Get the JSON imaginary value of the second rectangular complex number from a JSONObject
    protected Double getJSONBImaginary(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        try {
           return jsonObject.getDouble(B_IMAGINARY_KEY);
        } catch (Exception e) {
            handleInvalidDoubleValue(B_IMAGINARY_KEY, response);
        }
        return null;
    }

    // Get the return type from a JSONObject
    protected String getJSONReturnType(JSONObject jsonObject, HttpServletResponse response) throws IOException {
        String returnType= jsonObject.getString(RETURN_TYPE_KEY);
        if (!isValidReturnType(returnType)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJSONData(response, "{\"error\":\"Invalid return type. Must be 'rectangular' or 'polar'\"}");
            return null;
        }
        return returnType;
    }

    // Check if the return type is valid
    protected boolean isValidReturnType(String returnType) {
        return returnType.equals(RETURN_TYPE_RECTANGULAR) || returnType.equals(RETURN_TYPE_POLAR);
    }
}
