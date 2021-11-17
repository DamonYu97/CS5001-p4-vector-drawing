/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;

/**
 * @author 200011181
 * @version 1.0
 */
public abstract class Shape {
    protected Color color;
    protected int startPointX;
    protected int startPointY;
    protected int endPointX;
    protected int endPointY;

    public Shape() {
        //default
        this(Color.black, 0 ,0, 0, 0);
    }

    public Shape(Color color) {
        this(color, 0 ,0, 0, 0);
    }

    public Shape(Color color, int startPointX, int startPointY, int endPointX, int endPointY) {
        this.color = color;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.endPointX = endPointX;
        this.endPointY = endPointY;
    }

    public abstract void draw(Graphics g);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setEndPoint(int endPointX, int endPointY) {
        this.endPointX = endPointX;
        this.endPointY = endPointY;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color=" + color +
                ", startPointX=" + startPointX +
                ", startPointY=" + startPointY +
                ", endPointX=" + endPointX +
                ", endPointY=" + endPointY +
                '}';
    }
}
