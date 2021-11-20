package model;

import util.ListUtil;
import model.shape.*;
import model.shape.Shape;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * CanvasModel class represents the model for the overall vector drawing program.
 * @author 200011181
 * @version 1.0
 */
public class CanvasModel extends GeneralModel {
    public static final int SELECT_MODE = 0;
    public static final int DRAW_MODE = 1;
    private List<Shape> shapes;
    /**
     * undoList backups all deserialized previous shapes lists for undo action.
     */
    private Stack<byte[]> undoList;
    /**
     * redoList backups all deserialized shapes lists for redo action.
     */
    private Stack<byte[]> redoList;
    /**
     * color for current drawing shape or selected shape.
     */
    private Color color;
    /**
     * fill state for current drawing shape or selected shape.
     */
    private boolean isFilled;
    /**
     * shape type of current drawing shape.
     */
    private ShapeType shapeType;
    /**
     * current drawing shape.
     */
    private Shape shape;
    private Shape selectedShape;
    private int mode;
    private ListUtil<Shape> listUtil;

    public CanvasModel(List<Shape> shapes, Color color, boolean isFilled, int mode) {
        super();
        this.shapes = shapes;
        this.selectedShape = null;
        this.color = color;
        this.isFilled = isFilled;
        this.mode = mode;
        undoList = new Stack<>();
        redoList = new Stack<>();
        listUtil = new ListUtil<>();
    }

    public CanvasModel() {
        //default model
        this(new ArrayList<>(), Color.black, false, SELECT_MODE);
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            //save current shapes list to redoList
            redoList.push(listUtil.serialize(shapes));
            //get the shapes list from undoList
            shapes = listUtil.deserialize(undoList.pop());
            changed();
        }
    }

    public void selectShape(double x, double y) {
        selectedShape = null;
        //find if this point(x, y) is on any shapes in the canvas shapes list
        //if this point(x, y) is on more than one shapes, then it will select the most recent one.
        for (int i = shapes.size() - 1; i >= 0 ; i--) {
            Shape shape = shapes.get(i);
            if (shape.contain(x, y)) {
                selectedShape = shape;
                return;
            }
        }
    }

    public void cleanup() {
        addUndo();
        shapes.clear();
        selectedShape = null;
        changed();
    }

    public void drag(int offSetX, int offsetY, Point initStart, Point initEnd) {
        if (selectedShape != null) {
            selectedShape.move(offSetX, offsetY, initStart, initEnd);
            changed();
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            //save current shapes list to undoList
            undoList.push(listUtil.serialize(shapes));
            //get shapes list from redoList
            shapes = listUtil.deserialize(redoList.pop());
            changed();
        }
    }

    public void save(File toFile) throws IOException {
        //get the file
        String fileName = toFile.getName();
        if (!fileName.endsWith(".vec")) {
            toFile = new File(toFile.getParentFile(), fileName + ".vec");
        }
        //write shapes to file
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(toFile));
        out.writeObject(shapes);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load(File fromFile) throws IOException, ClassNotFoundException {
        addUndo();
        //get the file
        Path filePath = fromFile.toPath();
        if (!filePath.toString().endsWith(".vec") || Files.notExists(filePath)) {
            return;
        }
        //reset shapes from file
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fromFile));
        this.shapes = (List<Shape>)inputStream.readObject();
        inputStream.close();
        changed();
    }

    public void createShape(int startPointX, int startPointY) {
        if (shapeType == null) {
            return;
        }
        //create a shape according to the shapeType and other parameters.
        shape = ShapeFactory.createShape(shapeType, color, isFilled,
                startPointX, startPointY, startPointX, startPointY);
    }

    public void setEndPoint(int endPointX, int endPointY) {
        if (shape == null) {
            return;
        }
        //update the endpoint of current drawing shape.
        shape.setEndPoint(endPointX, endPointY);
        changed();
    }

    public void drawCurrentShape(Graphics g) {
        this.shape.draw((Graphics2D)g);
    }

    public void drawAllShapes(Graphics g) {
        for (Shape shape: shapes) {
            shape.draw((Graphics2D) g);
        }
    }

    /**
     * add shapes list to undoList for undo
     */
    private void addUndo() {
        //when new action performs, then back up to undoList and clear redoList.
        redoList.clear();
        //save current shapes list to undoList
        undoList.push(listUtil.serialize(shapes));
    }

    public void startDrag() {
        addUndo();
    }

    public void finishedDrawing() {
        if (this.shape == null) {
            return;
        }
        Shape finishedShape = this.shape;
        addUndo();
        shapes.add(finishedShape);
        this.shape = null;
        changed();
    }

    public void lockRatio(boolean lock) {
        if (shapeType == null && shape == null) {
            return;
        }
        //if the shape is lockable, then lock the ratio
        if (shape instanceof Lockable) {
            ((Lockable)shape).enableLock(lock);
        }
    }

    public void setColor(Color color) {
        Color oldColor = this.color;
        this.color = color;
    }

    public void updateSelectedShapeColor() {
        if (selectedShape != null) {
            addUndo();
            selectedShape.setColor(color);
            changed();
        }
    }

    public void changeFilledState() {
        isFilled = !isFilled;
    }

    public void changeSelectedFilledState() {
        if (selectedShape != null) {
            addUndo();
            //change the filled state without changing the color.
            //the filled color will be the same as the original one instead of the color stored here.
            selectedShape.setFilled(!selectedShape.getFilled());
            changed();
        }
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
        setMode(DRAW_MODE);
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Shape getShape() {
        return shape;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Color getColor() {
        return color;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public int getMode() {
        return mode;
    }

}
