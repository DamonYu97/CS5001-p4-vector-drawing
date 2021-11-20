package shape_tests;

import model.shape.Rectangle;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author 200011181
 * @version 1.0
 */
public class RectangleTest {

    @Test
    public void testCreate() {
        Color color = Color.BLUE;
        boolean isFilled = false;
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Rectangle rectangle = new Rectangle(color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertEquals(color, rectangle.getColor());
        assertEquals(isFilled, rectangle.getFilled());
        assertEquals(start, rectangle.getStartPoint());
        assertEquals(end, rectangle.getEndPoint());
        assertNotNull(rectangle.getShape());
        assertEquals(40, rectangle.getHeight());
        assertEquals(50, rectangle.getWidth());
    }

    @Test
    public void testLockRatio() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Rectangle rectangle = new Rectangle(start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertNotNull(rectangle.getShape());
        assertFalse("this rectangle should not be locked", rectangle.isLockRatio());
        assertEquals(40, rectangle.getHeight());
        assertEquals(50, rectangle.getWidth());
        rectangle.enableLock(true);
        assertTrue("this rectangle should be locked", rectangle.isLockRatio());
        assertEquals(40, rectangle.getHeight());
        assertEquals(40, rectangle.getWidth());
        rectangle.enableLock(false);
        assertFalse("this rectangle should not be locked", rectangle.isLockRatio());
        assertEquals(40, rectangle.getHeight());
        assertEquals(50, rectangle.getWidth());
    }

    @Test
    public void testCreateWithDefaultColorFill() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Rectangle rectangle = new Rectangle(start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertEquals(Color.black, rectangle.getColor());
        assertFalse(rectangle.getFilled());
    }

    @Test
    public void testPointInTheRectangle() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Rectangle rectangle = new Rectangle(start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertTrue("Point(1,1) should be in the " + rectangle,
                rectangle.contain(1, 1));
        assertTrue("Point(45,32) should be in the " + rectangle,
                rectangle.contain(45, 32));
        Point middle = new Point(25, 20);
        assertTrue("Point(" + middle.x + "," + middle.y + ") should be in the " + rectangle,
                rectangle.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheRectangle() {
        Point start = new Point(1,1);
        Point end = new Point(50, 40);
        Rectangle rectangle = new Rectangle(start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertFalse("Point(0, 0) should not be in the " + rectangle,
                rectangle.contain(0, 0));
        assertFalse("Point(0, 41) should not be in the " + rectangle,
                rectangle.contain(1, 41));
        assertFalse("Point(52, 4) should not be in the " + rectangle,
                rectangle.contain(52, 4));
        assertFalse("Point(60, 60) should not be in the " + rectangle,
                rectangle.contain(60, 60));

    }
}
