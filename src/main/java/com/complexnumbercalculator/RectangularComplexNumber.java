package com.complexnumbercalculator;

// Rectangular form of a complex number (a + bi) where a is the real part and b is the imaginary part
public class RectangularComplexNumber {
    private double real;
    private double imaginary;

    public RectangularComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public String toString() {
        return real + " + " + imaginary + "i";
    }

    // Get the real part of the complex number
    public double getReal() {
        return real;
    }

    // Get the imaginary part of the complex number
    public double getImaginary() {
        return imaginary;
    }

    // Set the real part of the complex number
    public void setReal(double real) {
        this.real = real;
    }

    // Set the imaginary part of the complex number
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    // Add a complex number to a real number
    public static RectangularComplexNumber add(RectangularComplexNumber a, double b) {
        return new RectangularComplexNumber(a.real + b, a.imaginary);
    }

    // Add a real number to a complex number
    public static RectangularComplexNumber add(double a, RectangularComplexNumber b) {
        return new RectangularComplexNumber(a + b.real, b.imaginary);
    }

    // Add two complex numbers
    public static RectangularComplexNumber add(RectangularComplexNumber a, RectangularComplexNumber b) {
        return new RectangularComplexNumber(a.real + b.real, a.imaginary + b.imaginary);
    }

    // Subtract a real number to a complex number
    public static RectangularComplexNumber subtract(RectangularComplexNumber a, double b) {
        return new RectangularComplexNumber(a.real - b, a.imaginary);
    }

    // Subtract a complex number to a real number
    public static RectangularComplexNumber subtract(double a, RectangularComplexNumber b) {
        return new RectangularComplexNumber(a - b.real, -b.imaginary);
    }

    // Subtract two complex numbers
    public static RectangularComplexNumber subtract(RectangularComplexNumber a, RectangularComplexNumber b) {
        return new RectangularComplexNumber(a.real - b.real, a.imaginary - b.imaginary);
    }

    // Product of a complex number by a real number
    public static RectangularComplexNumber product(RectangularComplexNumber a, double b) {
        return new RectangularComplexNumber(a.real * b, a.imaginary*b);
    }

    // Product of a real number by a complex number
    public static RectangularComplexNumber product(double a, RectangularComplexNumber b) {
        return new RectangularComplexNumber(a * b.real, b.imaginary*a);
    }

    // Product of two complex numbers
    public static RectangularComplexNumber product(RectangularComplexNumber a, RectangularComplexNumber b) {
        return new RectangularComplexNumber((a.real*b.real)-(a.imaginary*b.imaginary), (a.real*b.imaginary)+(a.imaginary*b.real));
    }

    // Division of a complex number by a real number
    public static RectangularComplexNumber divide(RectangularComplexNumber a, double b) {
        return new RectangularComplexNumber(a.real / b, a.imaginary/b);
    }

    // Division of a real number by a complex number
    public static RectangularComplexNumber divide(double a, RectangularComplexNumber b) {
        double squareBReal=b.real*b.real;
        double squareBImaginary=b.imaginary*b.imaginary;

        return new RectangularComplexNumber((a*b.real)/(squareBReal+squareBImaginary), -(a*b.imaginary)/(squareBReal+squareBImaginary));
    }

    // Division of two complex numbers
    public static RectangularComplexNumber divide(RectangularComplexNumber a, RectangularComplexNumber b) {
        return RectangularComplexNumber.divide(
                new RectangularComplexNumber((a.real * b.real) + (a.imaginary * b.imaginary), (a.imaginary * b.real) - (a.real * b.imaginary)),
                (b.real * b.real) + (b.imaginary * b.imaginary)
        );
    }

    // Convert a polar complex number to a rectangular complex number
    public static RectangularComplexNumber fromPolar(PolarComplexNumber polarComplexNumber) {
        return new RectangularComplexNumber(
                polarComplexNumber.getMagnitude() * Math.cos(polarComplexNumber.getAngle()),
                polarComplexNumber.getMagnitude() * Math.sin(polarComplexNumber.getAngle())
        );
    }

    // Convert a rectangular complex number to a polar complex number
    public static PolarComplexNumber toPolar(RectangularComplexNumber rectangularComplexNumber) {
        return new PolarComplexNumber(
                Math.sqrt(rectangularComplexNumber.real * rectangularComplexNumber.real + rectangularComplexNumber.imaginary * rectangularComplexNumber.imaginary),
                Math.atan2(rectangularComplexNumber.imaginary, rectangularComplexNumber.real)
        );
    }
}
