package shape_tests;

import model.shape.Line;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class LineTest {

    @Test
    public void testCreate() {
        Color color = Color.BLUE;
        boolean isFilled = false;
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertEquals(color, line.getColor());
        assertEquals(isFilled, line.getFilled());
        assertEquals(start, line.getStartPoint());
        assertEquals(end, line.getEndPoint());
        assertNotNull(line.getShape());
    }

    @Test
    public void testCreateWithDefaultColorFill() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertEquals(Color.black, line.getColor());
        assertFalse(line.getFilled());
    }


    /*@Test
    public void testPointInTheLineForSolutionOne() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertTrue("Start Point(" + start.x + "," + start.y + ") should be in the " + line,
                line.contain(start.x, start.y));
        assertTrue("End Point(" + end.x + "," + end.y + ") should be in the " + line,
                line.contain(end.x, end.y));
        Point middle = new Point(25, 20);
        assertTrue("Middle Point(" + middle.x + "," + middle.y + ") should be in the " + line,
                line.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheLineForSolutionOne() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertFalse("Point(" + 0 + "," + 1 + ") should not be in the " + line,
                line.contain(0, 1));
        assertFalse("Point(" + 20 + "," + 20 + ") should not be in the " + line,
                line.contain(20, 20));
    }*/

    @Test
    public void testPointInTheLineForSolutionTwo() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertTrue("Point(0.5, 0.5) should be in the " + line,
                line.contain(0.5, 0.5));
        assertTrue("End Point(49.9, 39.9) should be in the " + line,
                line.contain(49.9,39.9));
        Point middle = new Point(25, 20);
        assertTrue("Middle Point(" + middle.x + "," + middle.y + ") should be in the " + line,
                line.contain(middle.x, middle.y));
    }

    @Test
    public void testPointOutTheLineForSolutionTwo() {
        Point start = new Point(0,0);
        Point end = new Point(50, 40);
        Line line = new Line(start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertFalse("Point(3,0) should not be in the " + line,
                line.contain(3, 0));
        assertFalse("Point(" + 22 + "," + 22 + ") should not be in the " + line,
                line.contain(22, 22));
    }

}
