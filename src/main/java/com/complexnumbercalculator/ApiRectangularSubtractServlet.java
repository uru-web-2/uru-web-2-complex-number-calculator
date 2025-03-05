package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet that handles the subtraction of two rectangular complex numbers
@WebServlet(urlPatterns = {"/api/rectangular/subtract"})
public class ApiRectangularSubtractServlet extends ApiRectangularServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.calculate(request, response, RectangularComplexNumber::subtract);
    }
}
