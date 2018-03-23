package com.wfy.work2.equilateral;

/**
 * Created by wfy on 18-3-23, good luck.
 */
public class Main {
    public static void main(String[] args) {
        EquilateralFigure ef = new Triangle(5);
        double triangleArea = ef.getArea();
        ef = new Square(2);
        double squareArea = ef.getArea();
        ef = new Round(10);
        double roundArea = ef.getArea();
    }
}
