package shape_tests;

import model.shape.Rectangle;
import model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author 200011181
 * @version 1.0
 */
public class ShapeTest {
    private Shape shape;
    Point start = new Point(0,0);
    Point end = new Point(50, 40);
    @Before
    public void setup() {
        shape = new Rectangle(start.x, start.y, end.x, end.y);
    }

    @Test
    public void testEndPointChanged() {
        assertNotNull(shape);
        assertEquals(end, shape.getEndPoint());
        Point newEndPoint = new Point(30, 60);
        //set new end point
        shape.setEndPoint(newEndPoint.x, newEndPoint.y);
        assertEquals(newEndPoint, shape.getEndPoint());
        Rectangle rectangle = new Rectangle(start.x, start.y, newEndPoint.x, newEndPoint.y);
        assertEquals(shape.getShape(), rectangle.getShape());
    }

    @Test
    public void testMove() {
        assertNotNull(shape);
        int offsetX = 9;
        int offsetY = 5;
        Point initStartPoint = start;
        Point initEndPoint = end;
        assertNotNull(shape.getShape());
        assertEquals(initStartPoint, shape.getStartPoint());
        assertEquals(initEndPoint, shape.getEndPoint());
        //move
        shape.move(offsetX, offsetY, initStartPoint, initEndPoint);
        Shape moveShape = new Rectangle(initStartPoint.x + offsetX, initStartPoint.y + offsetY,
                                            initEndPoint.x + offsetX, initEndPoint.y + offsetY);
        assertEquals(moveShape.getShape(), shape.getShape());
    }


}
