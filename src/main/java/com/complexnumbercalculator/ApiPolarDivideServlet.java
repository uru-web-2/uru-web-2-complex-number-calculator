package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet that handles the division of two polar complex numbers
@WebServlet(urlPatterns = {"/api/polar/divide"})
public class ApiPolarDivideServlet extends ApiPolarServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.calculate(request, response, PolarComplexNumber::divide);
    }
}