/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @author 200011181
 * @version 1.0
 */
public class Ellipse extends Shape implements Lockable {
    private int height;
    private int width;
    private boolean lockSquare;

    public Ellipse(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
        lockSquare = false;
    }

    @Override
    protected void update() {
        height = Math.abs(endPointY - startPointY);
        width = Math.abs(endPointX - startPointX);
        if (lockSquare) {
            if (height > width) {
                height = width;
            } else {
                width = height;
            }
        }
        int x = startPointX;
        int y = startPointY;
        if (endPointY - startPointY < 0) {
            y = endPointY;
        }
        if (endPointX - startPointX < 0 ) {
            x = endPointX;
        }
        shape = new Ellipse2D.Double(x, y, width, height);
    }


    @Override
    public void enableLock(boolean lock) {
        lockSquare = lock;
    }
}
