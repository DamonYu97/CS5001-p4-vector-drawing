/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class DiagonalCross extends Shape {
    public DiagonalCross(Color color, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    public void draw(Graphics g) {
        int xMove = (endPointX - startPointX) / 4;
        int yMove = (endPointY - startPointY) / 4;

        int x1 = startPointX + xMove;
        int y1 = startPointY - yMove;
        int x2 = endPointX + xMove;
        int y2 = endPointY - yMove;

        int interX1 = startPointX + xMove * 3 / 2;
        int interY1 = startPointY + yMove * 3 / 2;
        g.drawLine(startPointX, startPointY, interX1, interY1);
        g.drawLine(interX1 + xMove, interY1 + yMove, endPointX, endPointY);
        g.drawLine(x1, y1, interX1 + xMove, interY1 - yMove);
        g.drawLine(interX1 + xMove * 2, interY1, x2, y2);
        g.drawLine(startPointX, startPointY, x1, y1);
        g.drawLine(endPointX, endPointY, x2, y2);

        g.drawLine(startPointX, y2, interX1, interY1);
        g.drawLine(interX1 + xMove, interY1 - yMove, endPointX, y1);
        g.drawLine(x1, endPointY, interX1 + xMove, interY1 + yMove);
        g.drawLine(interX1 + xMove * 2, interY1, x2, startPointY);
        g.drawLine(startPointX, y2, x1, endPointY);
        g.drawLine(endPointX, y1, x2, startPointY);
        g.setColor(color);
    }
}
