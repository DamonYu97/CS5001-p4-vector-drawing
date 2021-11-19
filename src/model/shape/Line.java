/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * @author 200011181
 * @version 1.0
 */
public class Line extends Shape {

    public Line(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    protected void update() {
        shape = new Line2D.Double(startPointX, startPointY, endPointX, endPointY);
    }
}
