package com.wfy.work2.equilateral;

/**
 * Created by wfy on 18-3-23, good luck.
 */
public class Round extends EquilateralFigure {
    public Round(double edge) {
        super(edge);
    }

    @Override
    public double getArea() {
        return Math.PI / 2 * getEdge() * getEdge();
    }
}
