package model.shape;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * @author 200011181
 * @version 1.0
 */
public class Line extends Shape {

    public Line(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
    }

    public Line(int startPointX, int startPointY, int endPointX, int endPointY) {
        this(Color.black, false, startPointX, startPointY, endPointX, endPointY);
    }

    /**
     * This will test if the point(x,y) is on this line
     * @param x x coordinate
     * @param y x coordinate
     * @return true if the point(x,y) is on this line
     */
    @Override
    public boolean contain(double x, double y) {
        /*
        Solution 1 to solve the double compare with int problem
        boolean result = true;
        if ((x - startPointX) * (startPointY - endPointY) != (startPointX - endPointX) * (y - startPointY)) {
            result = false;
        }
        if (endPointY - startPointY > 0) {
            if (y < startPointY || y > endPointY) {
                result = false;
            }
        } else {
            if (y < endPointY || y > startPointY) {
                result = false;
            }
        }

        if (endPointX - startPointX > 0) {
            if (x < startPointX || x > endPointX) {
                result = false;
            }
        } else {
            if (x < endPointX || y > startPointX) {
                result = false;
            }
        }
        return result;
        */

        /*
        Solution 2 to solve line it to small to select problem.
        extend the line to a rectangle.
         */
        Path2D rectangle = new GeneralPath();
        int offset = 1;
        rectangle.moveTo(startPointX - offset, startPointY + offset);
        rectangle.lineTo(startPointX + offset, startPointY - offset);
        rectangle.lineTo(endPointX + offset, endPointY - offset);
        rectangle.lineTo(endPointX - offset, endPointY + offset);
        rectangle.closePath();
        return  rectangle.contains(x, y);

    }

    @Override
    protected void update() {
        shape = new Line2D.Double(startPointX, startPointY, endPointX, endPointY);
    }
}
