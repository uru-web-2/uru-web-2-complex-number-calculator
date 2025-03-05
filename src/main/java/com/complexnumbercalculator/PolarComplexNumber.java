package com.complexnumbercalculator;

// Polar form of a complex number (r * (cos(theta) + i * sin(theta))) where r is the magnitude and theta is the angle
public class PolarComplexNumber {
    private double magnitude;
    private double angle;

    // NOTE: The angle is in radians
    public PolarComplexNumber(double magnitude, double angle) {
        this.magnitude = magnitude;
        this.angle = angle;
    }

    public String toString() {
        return magnitude + " * (cos(" + angle + ") + i * sin(" + angle + "))";
    }

    // Get the magnitude of the complex number
    public double getMagnitude() {
        return magnitude;
    }

    // Get the angle of the complex number
    public double getAngle() {
        return angle;
    }

    // Set the magnitude of the complex number
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    // Set the angle of the complex number
    public void setAngle(double angle) {
        this.angle = angle;
    }

    // Add a complex number to a real number
    public static RectangularComplexNumber add(PolarComplexNumber a, double b) {
        return RectangularComplexNumber.add(a.toRectangular(), b);
    }

    // Add a real number to a complex number
    public static RectangularComplexNumber add(double a, PolarComplexNumber b) {
        return RectangularComplexNumber.add(a, b.toRectangular());
    }

    // Add a complex number to a real number
    public static RectangularComplexNumber add(PolarComplexNumber a, RectangularComplexNumber b) {
        return RectangularComplexNumber.add(a.toRectangular(), b);
    }

    // Add two complex numbers
    public static RectangularComplexNumber add(PolarComplexNumber a, PolarComplexNumber b) {
        RectangularComplexNumber aRectangular = a.toRectangular();
        RectangularComplexNumber bRectangular = b.toRectangular();
        return RectangularComplexNumber.add(aRectangular, bRectangular);
    }

    // Subtract a real number to a complex number
    public static RectangularComplexNumber subtract(PolarComplexNumber a, double b) {
        return RectangularComplexNumber.subtract(a.toRectangular(), b);
    }

    // Subtract a complex number to a real number
    public static RectangularComplexNumber subtract(double a, PolarComplexNumber b) {
        return RectangularComplexNumber.subtract(a, b.toRectangular());
    }

    // Subtract two complex numbers
    public static RectangularComplexNumber subtract(PolarComplexNumber a, PolarComplexNumber b) {
        return RectangularComplexNumber.subtract(a.toRectangular(), b.toRectangular());
    }

    // Multiply of a complex number by a real number
    public static RectangularComplexNumber multiply(PolarComplexNumber a, double b) {
        return RectangularComplexNumber.multiply(a.toRectangular(), b);
    }

    // Multiply of a real number by a complex number
    public static RectangularComplexNumber multiply(double a, PolarComplexNumber b) {
        return RectangularComplexNumber.multiply(a, b.toRectangular());
    }

    // Multiply of two complex numbers
    public static RectangularComplexNumber multiply(PolarComplexNumber a, PolarComplexNumber b) {
        return RectangularComplexNumber.multiply(a.toRectangular(), b.toRectangular());
    }

    // Division of a complex number by a real number
    public static RectangularComplexNumber divide(PolarComplexNumber a, double b) {
        return RectangularComplexNumber.divide(a.toRectangular(), b);
    }

    // Division of a real number by a complex number
    public static RectangularComplexNumber divide(double a, PolarComplexNumber b) {
        return RectangularComplexNumber.divide(a, b.toRectangular());
    }

    // Division of two complex numbers
    public static RectangularComplexNumber divide(PolarComplexNumber a, PolarComplexNumber b) {
        return RectangularComplexNumber.divide(a.toRectangular(), b.toRectangular());
    }

    // Convert a rectangular complex number to a polar complex number
    public static PolarComplexNumber fromRectangular(RectangularComplexNumber a) {
        double magnitude = Math.sqrt(a.getReal() * a.getReal() + a.getImaginary() * a.getImaginary());
        double angle = Math.atan2(a.getImaginary(), a.getReal());
        return new PolarComplexNumber(magnitude, angle);
    }

    // Convert a polar complex number to a rectangular complex number
    public RectangularComplexNumber toRectangular() {
        double real = magnitude * Math.cos(angle);
        double imaginary = magnitude * Math.sin(angle);
        return new RectangularComplexNumber(real, imaginary);
    }
}
