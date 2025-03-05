package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Servlet that handles the multiplication of two polar complex numbers
@WebServlet(urlPatterns = {"/api/polar/add"})
public class ApiPolarMultiplyServlet extends ApiPolarServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.calculate(request, response, PolarComplexNumber::multiply);
    }
}
