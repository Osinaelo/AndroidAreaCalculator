package com.example.andys.myapplication.figures;

public class Trapezium extends Figure {

    protected double a, b, h;

    public Trapezium(double dimensionX, double dimensionY, double height) {
        a = dimensionX;
        b = dimensionY;
        h = height;
    }

    public double area() {
        return (a + b) / 2.0 * h;
    }      
}
