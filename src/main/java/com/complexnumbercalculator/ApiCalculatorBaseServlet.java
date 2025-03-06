package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;

public class ApiCalculatorBaseServlet extends BaseServlet {
    static String A_MAGNITUDE_KEY = "a_magnitude";
    static String A_ANGLE_KEY = "a_angle";
    static String B_MAGNITUDE_KEY = "b_magnitude";
    static String B_ANGLE_KEY = "b_angle";
    static String A_REAL_KEY = "a_real";
    static String A_IMAGINARY_KEY = "a_imaginary";
    static String B_REAL_KEY = "b_real";
    static String B_IMAGINARY_KEY = "b_imaginary";
    static String RETURN_TYPE_KEY = "return_type";
    static String RETURN_TYPE_RECTANGULAR = "rectangular";
    static String RETURN_TYPE_POLAR = "polar";

    // Handle success JSON data writing to the response
    protected void handleWriteSuccessJSONData(HttpServletResponse response, PolarComplexNumber polarComplexNumber, String returnType) throws IOException {
        if (returnType.equals(RETURN_TYPE_POLAR)) {
            writeSuccessJSONData(response, polarComplexNumber.toJSONObject());
        } else {
            RectangularComplexNumber rectangularComplexNumber = polarComplexNumber.toRectangular();
            writeSuccessJSONData(response, rectangularComplexNumber.toJSONObject());
        }
    }

    // Handle success JSON data writing to the response
    protected void handleWriteSuccessJSONData(HttpServletResponse response, RectangularComplexNumber rectangularComplexNumber, String returnType) throws IOException {
        if (returnType.equals(RETURN_TYPE_POLAR)) {
            PolarComplexNumber polarComplexNumber = RectangularComplexNumber.toPolar(rectangularComplexNumber);
            writeSuccessJSONData(response, polarComplexNumber.toJSONObject());
        } else {
            writeSuccessJSONData(response, rectangularComplexNumber.toJSONObject());
        }
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
            writeFailJSONData(response, RETURN_TYPE_KEY,"Invalid return type. Must be 'rectangular' or 'polar'");
            return null;
        }
        return returnType;
    }

    // Check if the return type is valid
    protected boolean isValidReturnType(String returnType) {
        return returnType.equals(RETURN_TYPE_RECTANGULAR) || returnType.equals(RETURN_TYPE_POLAR);
    }
}
