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
    private boolean lockRatio;

    public Ellipse(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
        lockRatio = false;
    }

    public Ellipse(int startPointX, int startPointY, int endPointX, int endPointY) {
        this(Color.black, false, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    protected void update() {
        height = Math.abs(endPointY - startPointY);
        width = Math.abs(endPointX - startPointX);
        int changedX = 0;
        int changedY = 0;

        if (lockRatio) {
            if (height > width) {
                changedY = height - width;
                height = width;
            } else {
                changedX = width - height;
                width = height;
            }
        }
        int upperLeftX = startPointX;
        int upperLeftY = startPointY;
        if (endPointY - startPointY < 0) {
            upperLeftY = endPointY + changedY;
        }
        if (endPointX - startPointX < 0 ) {
            upperLeftX = endPointX + changedX;
        }
        shape = new Ellipse2D.Double(upperLeftX, upperLeftY, width, height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isLockRatio() {
        return lockRatio;
    }

    @Override
    public void enableLock(boolean lock) {
        lockRatio = lock;
        update();
    }
}
