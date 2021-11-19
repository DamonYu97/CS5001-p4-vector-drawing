/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

import java.awt.*;
import java.io.Serializable;

/**
 * @author 200011181
 * @version 1.0
 */
public abstract class Shape implements Serializable {
    protected Color color;
    protected boolean isFilled;
    protected int startPointX;
    protected int startPointY;
    protected int endPointX;
    protected int endPointY;
    protected java.awt.Shape shape;

    public Shape(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        this.color = color;
        this.isFilled = isFilled;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.endPointX = endPointX;
        this.endPointY = endPointY;
        update();
    }

    public void move(int offsetX, int offsetY, Point initStart, Point initEnd) {
        this.startPointX = initStart.x + offsetX;
        this.startPointY = initStart.y + offsetY;
        this.endPointX = initEnd.x + offsetX ;
        this.endPointY = initEnd.y + offsetY;
        update();
    }

    public boolean contain(int x, int y) {
        return shape.contains(x, y);
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.draw(shape);
        if (isFilled) {
            g.fill(shape);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void setEndPoint(int endPointX, int endPointY) {
        this.endPointX = endPointX;
        this.endPointY = endPointY;
        update();
    }

    protected abstract void update();

    public Point getStartPoint() {
        return new Point(startPointX, startPointY);
    }

    public Point getEndPoint() {
        return  new Point(endPointX, endPointY);
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
