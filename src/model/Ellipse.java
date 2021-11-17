/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class Ellipse extends Shape {
    public Ellipse(Color color, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    public void draw(Graphics g) {
        int x = startPointX;
        int y = startPointY;
        if (endPointY - startPointY < 0) {
            y = endPointY;
        }
        if (endPointX - startPointX < 0 ) {
            x = endPointX;
        }
        g.drawOval(x, y, Math.abs(endPointX - startPointX), Math.abs(endPointY - startPointY));
        g.setColor(color);
    }
}
