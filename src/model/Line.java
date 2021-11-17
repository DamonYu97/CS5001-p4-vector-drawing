/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class Line extends Shape {

    public Line(Color color, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(startPointX, startPointY, endPointX, endPointY);
        g.setColor(color);
    }
}
