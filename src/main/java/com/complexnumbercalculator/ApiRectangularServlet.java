package com.complexnumbercalculator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.BiFunction;

// Servlet that handles the operations with two rectangular complex numbers
public class ApiRectangularServlet extends BaseServlet {
    protected void calculate(HttpServletRequest request, HttpServletResponse response, BiFunction<RectangularComplexNumber, RectangularComplexNumber, RectangularComplexNumber> operator) throws IOException {
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

        // Get the real and imaginary number of the rectangular polar complex number
        Double aReal = getJSONAReal(jsonObject, response);
        if (aReal == null) {
            return;
        }
        Double aImaginary = getJSONAImaginary(jsonObject, response);
        if (aImaginary == null) {
            return;
        }

        // Get the real and imaginary number of the second rectangular complex number
        Double bReal = getJSONBReal(jsonObject, response);
        if (bReal == null) {
            return;
        }
        Double bImaginary = getJSONBImaginary(jsonObject, response);
        if (bImaginary == null) {
            return;
        }

        // Add the two rectangular complex numbers
        RectangularComplexNumber a = new RectangularComplexNumber(aReal, aImaginary);
        RectangularComplexNumber b = new RectangularComplexNumber(bReal, bImaginary);
        RectangularComplexNumber result = operator.apply(a, b);

        // Write the result as a JSON object
        handleWriteJSONData(response, result, returnType);
    }
}
