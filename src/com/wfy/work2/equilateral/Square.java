package com.wfy.work2.equilateral;

/**
 * Created by wfy on 18-3-23, good luck.
 */
public class Square extends EquilateralFigure {
    public Square(double edge) {
        super(edge);
    }

    @Override
    public double getArea() {
        return getEdge() * getEdge();
    }
}
