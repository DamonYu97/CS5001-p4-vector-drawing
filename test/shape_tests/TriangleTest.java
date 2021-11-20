package shape_tests;

import model.shape.Triangle;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author 200011181
 * @version 1.0
 */
public class TriangleTest {
    @Test
    public void testCreate() {
        Color color = Color.BLUE;
        boolean isFilled = false;
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Triangle triangle = new Triangle(color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(triangle);
        assertEquals(color, triangle.getColor());
        assertEquals(isFilled, triangle.getFilled());
        assertEquals(start, triangle.getStartPoint());
        assertEquals(end, triangle.getEndPoint());
        assertNotNull(triangle.getShape());
    }

    @Test
    public void testCreateWithDefaultColorFill() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Triangle triangle = new Triangle(start.x, start.y, end.x, end.y);
        assertNotNull(triangle);
        assertEquals(Color.black, triangle.getColor());
        assertFalse(triangle.getFilled());
    }

    @Test
    public void testPointInTheTriangle() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Triangle triangle = new Triangle(start.x, start.y, end.x, end.y);
        assertNotNull(triangle);
        assertTrue("Point(25,1) should be in the " + triangle,
                triangle.contain(25, 1));
        assertTrue("Point(20,10) should be in the " + triangle,
                triangle.contain(20, 10));
        Point middle = new Point(25, 20);
        assertTrue("Point(" + middle.x + "," + middle.y + ") should be in the " + triangle,
                triangle.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheTriangle() {
        Point start = new Point(1,1);
        Point end = new Point(50, 40);
        Triangle triangle = new Triangle(start.x, start.y, end.x, end.y);
        assertNotNull(triangle);
        assertFalse("Point(0, 0) should not be in the " + triangle,
                triangle.contain(0, 0));
        assertFalse("Point(0, 41) should not be in the " + triangle,
                triangle.contain(1, 41));
        assertFalse("Point(52, 4) should not be in the " + triangle,
                triangle.contain(52, 4));
        assertFalse("Point(60, 60) should not be in the " + triangle,
                triangle.contain(60, 60));
        assertFalse("Point(2, 3) should not be in the " + triangle,
                triangle.contain(2, 3));
        assertFalse("Point(35, 10) should not be in the " + triangle,
                triangle.contain(35, 10));

    }
}
