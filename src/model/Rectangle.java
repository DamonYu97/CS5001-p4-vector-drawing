/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class Rectangle extends Shape implements Lockable {
    private int height;
    private int width;
    private boolean lockSquare;

    public Rectangle(Color color, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, startPointX, startPointY, endPointX, endPointY);
        lockSquare = false;
        height = Math.abs(endPointY - startPointY);
        width = Math.abs(endPointX - startPointX);
    }

    @Override
    public void setEndPoint(int endPointX, int endPointY) {
        super.setEndPoint(endPointX, endPointY);
        height = Math.abs(endPointY - startPointY);
        width = Math.abs(endPointX - startPointX);
    }

    @Override
    public void draw(Graphics g) {
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
        g.drawRect(x, y, width, height);
        g.setColor(color);
    }

    @Override
    public void enableLock(boolean lock) {
        lockSquare = lock;
    }

}
