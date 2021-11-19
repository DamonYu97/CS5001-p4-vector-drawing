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
public class Cross extends Shape {
    public Cross(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
    }
    @Override
    protected void update() {
        int xMove = (endPointX - startPointX) / 4;
        int yMove = (endPointY - startPointY) / 4;

        int x1 = startPointX + xMove;
        int y1 = startPointY - yMove;
        int x2 = endPointX + xMove;
        int y2 = endPointY - yMove;

        int interX1 = startPointX + xMove * 3 / 2;
        int interY1 = startPointY + yMove * 3 / 2;
        Path2D path = new Path2D.Double();
        path.moveTo(startPointX, startPointY);
        path.lineTo(x1, y1);
        path.lineTo(interX1 + xMove, interY1 - yMove);
        path.lineTo(endPointX, y1);
        path.lineTo(x2, startPointY);
        path.lineTo(interX1 + xMove * 2, interY1);
        path.lineTo(x2, y2);
        path.lineTo(endPointX, endPointY);
        path.lineTo(interX1 + xMove, interY1 + yMove);
        path.lineTo(x1, endPointY);
        path.lineTo(startPointX, y2);
        path.lineTo(interX1, interY1);
        path.closePath();
        shape = path;
    }
}
