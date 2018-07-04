package com.example.andys.myapplication.figures;

public class Ellipse extends Figure {

    protected double a, b;

    public Ellipse(double dimensionX, double dimensionY) {
        a = dimensionX;
        b = dimensionY;
    }

    public double area() {
        return Math.PI * a * b;
    }      
}
