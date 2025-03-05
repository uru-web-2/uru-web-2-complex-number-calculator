package com.complexnumbercalculator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

// Servlet that handles the addition of two polar complex numbers
@WebServlet(urlPatterns = {"/api/polar/add"})
public class ApiPolarAddServlet extends ApiPolarServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.calculate(request, response, PolarComplexNumber::add);
    }
}
