package com.wfy.work2.equilateral;

/**
 * Created by wfy on 18-3-23, good luck.
 */
public class Triangle extends EquilateralFigure {
    public Triangle(double edge) {
        super(edge);
    }

    @Override
    public double getArea() {
        return Math.sqrt(3) / 4.0 * getEdge() * getEdge();
    }
}
