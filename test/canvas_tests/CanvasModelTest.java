package canvas_tests;

import model.CanvasModel;
import model.shape.Line;
import model.shape.Rectangle;
import model.shape.ShapeType;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author 200011181
 * @version 1.0
 */
public class CanvasModelTest {
    private CanvasModel model;

    @Before
    public void setup() {
        model = new CanvasModel();
    }

    @Test
    public void testSelectDrawingShapeType() {
        //check default setting
        assertEquals(CanvasModel.SELECT_MODE, model.getMode());
        assertEquals(Color.black, model.getColor());
        assertFalse(model.isFilled());
        assertNull(model.getShapeType());
        //set shape type
        model.setShapeType(ShapeType.LINE);
        assertNotNull(model.getShapeType());
        assertEquals(ShapeType.LINE, model.getShapeType());
        assertEquals(CanvasModel.DRAW_MODE, model.getMode());
    }

    @Test
    public void testDrawLine() {
        assertEquals(0, model.getShapes().size());
        //set shape type to line
        model.setShapeType(ShapeType.LINE);
        assertEquals(ShapeType.LINE, model.getShapeType());
        assertEquals(CanvasModel.DRAW_MODE, model.getMode());
        //create a line with start draw point
        model.createShape(0, 0);
        assertNotNull(model.getShape());
        assertTrue("this shape should be a line", model.getShape() instanceof Line);
        assertEquals(new Point(0, 0), model.getShape().getEndPoint());
        //set the endpoint of this line
        model.setEndPoint(30, 30);
        assertEquals(new Point(30, 30), model.getShape().getEndPoint());
        //finish drawing
        assertEquals(0, model.getShapes().size());
        model.finishedDrawing();
        assertEquals(1, model.getShapes().size());
        assertEquals(new Point(30, 30), model.getShapes().get(0).getEndPoint());
        assertNull(model.getShape());
    }

    @Test
    public void testUndo() {
        //draw a line
        assertEquals(0, model.getShapes().size());
        drawALine();
        assertEquals(1, model.getShapes().size());
        //undo
        model.undo();
        assertEquals(0, model.getShapes().size());
    }

    @Test
    public void testRedo() {
        //draw a line
        assertEquals(0, model.getShapes().size());
        drawALine();
        assertEquals(1, model.getShapes().size());
        //undo
        model.undo();
        assertEquals(0, model.getShapes().size());
        //redo
        model.redo();
        assertEquals(1, model.getShapes().size());
        assertEquals(new Point(30, 30), model.getShapes().get(0).getEndPoint());
    }

    private void drawALine() {
        //set shape type to line
        model.setShapeType(ShapeType.LINE);
        //create a line with start draw point
        model.createShape(0, 0);
        //set the endpoint of this line
        model.setEndPoint(30, 30);
        //finish drawing
        model.finishedDrawing();
    }

    @Test
    public void testDrawARedLine() {
        assertEquals(Color.black, model.getColor());
        //draw a black line as default one
        drawALine();
        assertEquals(1, model.getShapes().size());
        assertEquals(Color.black, model.getShapes().get(0).getColor());
        //draw a red line
        model.setColor(Color.red);
        assertEquals(Color.red, model.getColor());
        drawALine();
        assertEquals(2, model.getShapes().size());
        assertEquals(Color.red, model.getShapes().get(1).getColor());
    }

    @Test
    public void testDrawAFilledLine() {
        assertFalse(model.isFilled());
        //draw an unfilled line as default one
        drawALine();
        assertEquals(1, model.getShapes().size());
        assertFalse(model.getShapes().get(0).getFilled());
        //draw a filled line
        model.changeFilledState();
        assertTrue(model.isFilled());
        drawALine();
        assertEquals(2, model.getShapes().size());
        assertTrue(model.getShapes().get(1).getFilled());
    }

    @Test
    public void testDrawASquareWithLockedRatio() {
        //draw a rectangle
        drawARectangle(false);
        assertEquals(1, model.getShapes().size());
        assertEquals(40, ((Rectangle)(model.getShapes().get(0))).getHeight());
        assertFalse(((Rectangle)(model.getShapes().get(0))).isLockRatio());
        //draw a square with locked ratio
        drawARectangle(true);
        assertEquals(2, model.getShapes().size());
        assertEquals(30, ((Rectangle)(model.getShapes().get(1))).getHeight());
        assertTrue(((Rectangle)(model.getShapes().get(1))).isLockRatio());
    }

    private void drawARectangle(boolean lock) {
        //set shape type to rectangle
        model.setShapeType(ShapeType.RECTANGLE);
        //create a line with start draw point
        model.createShape(0, 0);
        //set the endpoint of this rectangle
        if (lock) {
            model.lockRatio(lock);
        }
        model.setEndPoint(30, 40);
        //finish drawing
        model.finishedDrawing();
    }

    @Test
    public void testChangeSelectedLineColorToRed() {
        //draw a back line
        drawALine();
        assertEquals(1, model.getShapes().size());
        assertEquals(CanvasModel.DRAW_MODE, model.getMode());
        //set to select mode
        model.setMode(CanvasModel.SELECT_MODE);
        assertNull(model.getSelectedShape());
        //set the line
        model.selectShape(15, 15);
        assertNotNull(model.getSelectedShape());
        assertTrue(model.getSelectedShape() instanceof  Line);
        assertEquals(model.getSelectedShape(), model.getShapes().get(0));
        //set the color to red
        model.setColor(Color.red);
        assertEquals(Color.red, model.getColor());
        model.updateSelectedShapeColor();
        assertEquals(Color.red, model.getSelectedShape().getColor());
    }

    @Test
    public void testMoveSelectedLine() {
        //draw a back line
        drawALine();
        assertEquals(1, model.getShapes().size());
        assertEquals(CanvasModel.DRAW_MODE, model.getMode());
        //set to select mode
        model.setMode(CanvasModel.SELECT_MODE);
        assertNull(model.getSelectedShape());
        //select the line
        model.selectShape(15, 15);
        assertNotNull(model.getSelectedShape());
        assertTrue(model.getSelectedShape() instanceof  Line);
        assertEquals(model.getSelectedShape(), model.getShapes().get(0));
        //move by offset 5 x, 5 y
        model.drag(5, 5, new Point(0, 0), new Point(30, 30));
        assertEquals(new Point(5, 5), model.getSelectedShape().getStartPoint());
        assertEquals(new Point(35, 35), model.getSelectedShape().getEndPoint());
    }

    @Test
    public void testChangeFillStateOfASelectedRectangle() {
        //draw a back rectangle
        drawARectangle(false);
        assertEquals(1, model.getShapes().size());
        assertEquals(CanvasModel.DRAW_MODE, model.getMode());
        //set to select mode
        model.setMode(CanvasModel.SELECT_MODE);
        assertNull(model.getSelectedShape());
        //select the rectangle
        model.selectShape(15, 15);
        assertNotNull(model.getSelectedShape());
        assertTrue(model.getSelectedShape() instanceof Rectangle);
        assertEquals(model.getSelectedShape(), model.getShapes().get(0));
        assertFalse(model.getSelectedShape().getFilled());
        //change the fill state of this rectangle
        model.changeSelectedFilledState();
        assertTrue(model.getSelectedShape().getFilled());
        //change the fill state of this rectangle
        model.changeSelectedFilledState();
        assertFalse(model.getSelectedShape().getFilled());
    }

    @Test
    public void testSaveAndLoadCanvasToFile() throws IOException, ClassNotFoundException {
        File file = new File("first.vec");
        drawALine();
        drawARectangle(false);
        assertEquals(2, model.getShapes().size());
        model.save(file);
        //clean up canvas
        model.cleanup();
        assertEquals(0, model.getShapes().size());
        model.load(file);
        assertEquals(2, model.getShapes().size());
    }

}
