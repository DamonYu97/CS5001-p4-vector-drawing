package model.shape;

import java.awt.*;
import java.io.Serializable;

/**
 * Shape is an abstract class for 2D vector drawing
 * @author 200011181
 * @version 1.0
 */
public abstract class Shape implements Serializable {
    protected Color color;
    /**
     * fill state
     */
    protected boolean isFilled;
    /**
     * start drawing point x coordinate
     */
    protected int startPointX;
    /**
     * start drawing point y coordinate
     */
    protected int startPointY;
    /**
     * end drawing point x coordinate
     */
    protected int endPointX;
    /**
     * end drawing point y coordinate
     */
    protected int endPointY;
    /**
     * build-in shape object to represent shape path
     */
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

    /**
     * move the shape based on its original position and offset position
     * @param offsetX offset position x
     * @param offsetY offset position y
     * @param initStart original start drawing point
     * @param initEnd original end drawing point
     */
    public void move(int offsetX, int offsetY, Point initStart, Point initEnd) {
        this.startPointX = initStart.x + offsetX;
        this.startPointY = initStart.y + offsetY;
        this.endPointX = initEnd.x + offsetX ;
        this.endPointY = initEnd.y + offsetY;
        update();
    }

    public boolean contain(double x, double y) {
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

    /**
     * update the shape based on different shape type
     */
    protected abstract void update();

    public Point getStartPoint() {
        return new Point(startPointX, startPointY);
    }

    public Point getEndPoint() {
        return  new Point(endPointX, endPointY);
    }

    public java.awt.Shape getShape() {
        return shape;
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
