package com.complexnumbercalculator;

// Polar form of a complex number (r * (cos(theta) + i * sin(theta))) where r is the magnitude and theta is the angle
public class PolarComplexNumber {
    private double magnitude;
    private double angle;

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
    public static PolarComplexNumber add(PolarComplexNumber a, double b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.add(a.toRectangular(), b));
    }

    // Add a real number to a complex number
    public static PolarComplexNumber add(double a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.add(a, b.toRectangular()));
    }

    // Add a complex number to a real number
    public static PolarComplexNumber add(PolarComplexNumber a, RectangularComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.add(a.toRectangular(), b));
    }

    // Add two complex numbers
    public static PolarComplexNumber add(PolarComplexNumber a, PolarComplexNumber b) {
        RectangularComplexNumber aRectangular = a.toRectangular();
        RectangularComplexNumber bRectangular = b.toRectangular();
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.add(aRectangular, bRectangular));
    }

    // Subtract a real number to a complex number
    public static PolarComplexNumber subtract(PolarComplexNumber a, double b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.subtract(a.toRectangular(), b));
    }

    // Subtract a complex number to a real number
    public static PolarComplexNumber subtract(double a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.subtract(a, b.toRectangular()));
    }

    // Subtract two complex numbers
    public static PolarComplexNumber subtract(PolarComplexNumber a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.subtract(a.toRectangular(), b.toRectangular()));
    }

    // Product of a complex number by a real number
    public static PolarComplexNumber product(PolarComplexNumber a, double b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.product(a.toRectangular(), b));
    }

    // Product of a real number by a complex number
    public static PolarComplexNumber product(double a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.product(a, b.toRectangular()));
    }

    // Product of two complex numbers
    public static PolarComplexNumber product(PolarComplexNumber a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.product(a.toRectangular(), b.toRectangular()));
    }

    // Division of a complex number by a real number
    public static PolarComplexNumber divide(PolarComplexNumber a, double b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.divide(a.toRectangular(), b));
    }

    // Division of a real number by a complex number
    public static PolarComplexNumber divide(double a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.divide(a, b.toRectangular()));
    }

    // Division of two complex numbers
    public static PolarComplexNumber divide(PolarComplexNumber a, PolarComplexNumber b) {
        return PolarComplexNumber.fromRectangular(RectangularComplexNumber.divide(a.toRectangular(), b.toRectangular()));
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
