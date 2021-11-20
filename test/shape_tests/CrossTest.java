package shape_tests;

import model.shape.Cross;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author 200011181
 * @version 1.0
 */
public class CrossTest {
    @Test
    public void testCreate() {
        Color color = Color.BLUE;
        boolean isFilled = false;
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Cross cross = new Cross(color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(cross);
        assertEquals(color, cross.getColor());
        assertEquals(isFilled, cross.getFilled());
        assertEquals(start, cross.getStartPoint());
        assertEquals(end, cross.getEndPoint());
        assertNotNull(cross.getShape());
    }

    @Test
    public void testCreateWithDefaultColorFill() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Cross cross = new Cross(start.x, start.y, end.x, end.y);
        assertNotNull(cross);
        assertEquals(Color.black, cross.getColor());
        assertFalse(cross.getFilled());
    }

    @Test
    public void testPointInTheCross() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Cross cross = new Cross(start.x, start.y, end.x, end.y);
        assertNotNull(cross);
        assertTrue("Point(2,1) should be in the " + cross,
                cross.contain(2, 1));
        assertTrue("Point(40,30) should be in the " + cross,
                cross.contain(40, 30));
        assertTrue("Point(40,1) should be in the " + cross,
                cross.contain(40, 1));
        assertTrue("Point(2,30) should be in the " + cross,
                cross.contain(2, 30));
        assertTrue("Point(52, 4) should be in the " + cross,
                cross.contain(52, 4));
        Point middle = new Point(25, 20);
        assertTrue("Point(" + middle.x + "," + middle.y + ") should be in the " + cross,
                cross.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheCross() {
        Point start = new Point(1,1);
        Point end = new Point(50, 40);
        Cross cross = new Cross(start.x, start.y, end.x, end.y);
        assertNotNull(cross);
        assertFalse("Point(0, 0) should not be in the " + cross,
                cross.contain(0, 0));
        assertFalse("Point(0, 41) should not be in the " + cross,
                cross.contain(1, 41));
        assertFalse("Point(60, 4) should not be in the " + cross,
                cross.contain(60, 4));
        assertFalse("Point(60, 60) should not be in the " + cross,
                cross.contain(60, 60));
        assertFalse("Point(30, 3) should not be in the " + cross,
                cross.contain(30, 3));
        assertFalse("Point(25, 35) should not be in the " + cross,
                cross.contain(25, 35));
        assertFalse("Point(5, 20) should not be in the " + cross,
                cross.contain(5, 20));
        assertFalse("Point(50, 20) should not be in the " + cross,
                cross.contain(50, 20));
    }
}
