package model.shape;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * @author 200011181
 * @version 1.0
 */
public class Parallelogram extends Shape {
    /**
     * ratio of this parallelogram
     */
    private final int DEFAULT_RATIO = 4;
    public Parallelogram(Color color, boolean isFilled, int startPointX, int startPointY, int endPointX, int endPointY) {
        super(color, isFilled, startPointX, startPointY, endPointX, endPointY);
    }

    public Parallelogram(int startPointX, int startPointY, int endPointX, int endPointY) {
        this(Color.black, false, startPointX, startPointY, endPointX, endPointY);
    }

    @Override
    protected void update() {
        int xMove = (endPointX - startPointX) / DEFAULT_RATIO;
        Path2D path = new GeneralPath();
        path.moveTo(startPointX, startPointY);
        path.lineTo(endPointX - xMove, startPointY);
        path.lineTo(endPointX, endPointY);
        path.lineTo(startPointX + xMove, endPointY);
        path.closePath();
        shape = path;
    }
}
