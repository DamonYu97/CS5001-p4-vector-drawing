package shape_tests;

import model.shape.*;
import model.shape.Rectangle;
import model.shape.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author 200011181
 * @version 1.0
 */
public class ShapeFactoryTest {

    private Color color;
    private boolean isFilled;
    private Point start;
    private Point end;

    @Before
    public void setup() {
        color = Color.black;
        isFilled = false;
        start = new Point(50, 50);
        end = new Point(20, 80);
    }

    @Test
    public void testCreateLine() {
        Shape line = ShapeFactory.createShape(ShapeType.LINE, color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(line);
        assertTrue("this shape should be line", line instanceof Line);
        assertEquals(color, line.getColor());
        assertEquals(isFilled, line.getFilled());
        assertEquals(start, line.getStartPoint());
        assertEquals(end, line.getEndPoint());
        assertNotNull(line.getShape());
    }

    @Test
    public void testCreateRectangle() {
        Shape rectangle = ShapeFactory.createShape(ShapeType.RECTANGLE, color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(rectangle);
        assertTrue("this shape should be rectangle", rectangle instanceof Rectangle);
        assertEquals(color, rectangle.getColor());
        assertEquals(isFilled, rectangle.getFilled());
        assertEquals(start, rectangle.getStartPoint());
        assertEquals(end, rectangle.getEndPoint());
        assertNotNull(rectangle.getShape());
    }

    @Test
    public void testCreateTriangle() {
        Shape triangle = ShapeFactory.createShape(ShapeType.TRIANGLE, color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(triangle);
        assertTrue("this shape should be triangle", triangle instanceof Triangle);
        assertEquals(color, triangle.getColor());
        assertEquals(isFilled, triangle.getFilled());
        assertEquals(start, triangle.getStartPoint());
        assertEquals(end, triangle.getEndPoint());
        assertNotNull(triangle.getShape());
    }

    @Test
    public void testCreateCross() {
        Shape cross = ShapeFactory.createShape(ShapeType.CROSS, color, isFilled, start.x, start.y, end.x, end.y);
        assertNotNull(cross);
        assertTrue("this shape should be cross", cross instanceof Cross);
        assertEquals(color, cross.getColor());
        assertEquals(isFilled, cross.getFilled());
        assertEquals(start, cross.getStartPoint());
        assertEquals(end, cross.getEndPoint());
        assertNotNull(cross.getShape());
    }

}
