package shape_tests;

import model.shape.Ellipse;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author 200011181
 * @version 1.0
 */
public class EllipseTest {
    @Test
    public void testCreate() {
        Color color = Color.BLUE;
        boolean isFilled = false;
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Ellipse ellipse = new Ellipse(color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(ellipse);
        assertEquals(color, ellipse.getColor());
        assertEquals(isFilled, ellipse.getFilled());
        assertEquals(start, ellipse.getStartPoint());
        assertEquals(end, ellipse.getEndPoint());
        assertNotNull(ellipse.getShape());
        assertEquals(40, ellipse.getHeight());
        assertEquals(50, ellipse.getWidth());
    }

    @Test
    public void testLockRatio() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Ellipse ellipse = new Ellipse(start.x, start.y, end.x, end.y);
        assertNotNull(ellipse);
        assertNotNull(ellipse.getShape());
        assertFalse("this ellipse should not be locked", ellipse.isLockRatio());
        assertEquals(40, ellipse.getHeight());
        assertEquals(50, ellipse.getWidth());
        ellipse.enableLock(true);
        assertTrue("this ellipse should be locked", ellipse.isLockRatio());
        assertEquals(40, ellipse.getHeight());
        assertEquals(40, ellipse.getWidth());
        ellipse.enableLock(false);
        assertFalse("this ellipse should not be locked", ellipse.isLockRatio());
        assertEquals(40, ellipse.getHeight());
        assertEquals(50, ellipse.getWidth());
    }

    @Test
    public void testCreateWithDefaultColorFill() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Ellipse ellipse = new Ellipse(start.x, start.y, end.x, end.y);
        assertNotNull(ellipse);
        assertEquals(Color.black, ellipse.getColor());
        assertFalse(ellipse.getFilled());
    }

    @Test
    public void testPointInTheEllipse() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Ellipse ellipse = new Ellipse(start.x, start.y, end.x, end.y);
        assertNotNull(ellipse);
        assertTrue("Point(10,10) should be in the " + ellipse,
                ellipse.contain(10, 10));
        assertTrue("Point(45,20) should be in the " + ellipse,
                ellipse.contain(45, 20));
        Point middle = new Point(25, 20);
        assertTrue("Point(" + middle.x + "," + middle.y + ") should be in the " + ellipse,
                ellipse.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheEllipse() {
        Point start = new Point(1,1);
        Point end = new Point(50, 40);
        Ellipse ellipse = new Ellipse(start.x, start.y, end.x, end.y);
        assertNotNull(ellipse);
        assertFalse("Point(0, 0) should not be in the " + ellipse,
                ellipse.contain(0, 0));
        assertFalse("Point(0, 41) should not be in the " + ellipse,
                ellipse.contain(1, 41));
        assertFalse("Point(52, 4) should not be in the " + ellipse,
                ellipse.contain(52, 4));
        assertFalse("Point(60, 60) should not be in the " + ellipse,
                ellipse.contain(60, 60));
        assertFalse("Point(2, 3) should not be in the " + ellipse,
                ellipse.contain(2, 3));
        assertFalse("Point(49, 39) should not be in the " + ellipse,
                ellipse.contain(49, 39));

    }
}
