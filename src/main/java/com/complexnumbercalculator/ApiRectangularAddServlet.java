package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet that handles the addition of two rectangular complex numbers
@WebServlet(urlPatterns = {"/api/rectangular/add"})
public class ApiRectangularAddServlet extends ApiRectangularServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.calculate(request, response, RectangularComplexNumber::add);
    }
}
