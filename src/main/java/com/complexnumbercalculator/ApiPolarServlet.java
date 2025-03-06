package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.BiFunction;

// Servlet that handles the operations with two polar complex numbers
public class ApiPolarServlet extends ApiCalculatorBaseServlet {
    protected void calculate(HttpServletRequest request, HttpServletResponse response, BiFunction<PolarComplexNumber, PolarComplexNumber, RectangularComplexNumber> operator) throws IOException {
        // Get the JSON data from the request
        StringBuilder jsonBuffer = handleReadJSONData(request, response);
        if (jsonBuffer == null) {
            return;
        }

        // Parse JSON data
        JSONObject jsonObject = new JSONObject(jsonBuffer.toString());

        // Get the return type
        String returnType = getJSONReturnType(jsonObject, response);
        if (returnType == null) {
            return;
        }

        // Get the magnitude and angle of the first polar complex number
        Double aMagnitude = getJSONAMagnitude(jsonObject, response);
        if (aMagnitude == null) {
            return;
        }
        Double aAngle = getJSONAAngle(jsonObject, response);
        if (aAngle == null) {
            return;
        }

        // Get the magnitude and angle of the second polar complex number
        Double bMagnitude = getJSONBMagnitude(jsonObject, response);
        if (bMagnitude == null) {
            return;
        }
        Double bAngle = getJSONBAngle(jsonObject, response);
        if (bAngle == null) {
            return;
        }

        // Add the two polar complex numbers
        PolarComplexNumber a = new PolarComplexNumber(aMagnitude, aAngle);
        PolarComplexNumber b = new PolarComplexNumber(bMagnitude, bAngle);
        RectangularComplexNumber result = operator.apply(a, b);

        // Write the result as a JSON object
        handleWriteSuccessJSONData(response, result, returnType);
    }
}
