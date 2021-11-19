/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * @author 200011181
 * @version 1.0
 */
public class Triangle extends Shape {

    public Triangle(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    protected void update() {
        Path2D path = new Path2D.Double();
        path.moveTo(endPointX, endPointY);
        path.lineTo(startPointX, endPointY);
        path.lineTo(startPointX + (endPointX - startPointX) / 2, startPointY);
        path.closePath();
        shape = path;
    }
}
