package com.wfy.work2.equilateral;

public abstract class EquilateralFigure {
    private double edge;

    public EquilateralFigure(double edge) {
        this.edge = edge;
    }

    public double getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        this.edge = edge;
    }

    public abstract double getArea();
}
