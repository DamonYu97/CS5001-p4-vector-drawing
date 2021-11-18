/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author 200011181
 * @version 1.0
 */
public class CanvasModel extends AbstractModel {
    public static final int SELECT_MODE = 0;
    public static final int DRAW_MODE = 1;
    private List<Shape> shapes;
    private Stack<List<Shape>> undoList;
    private Stack<List<Shape>> redoList;
    private Color color;
    private Boolean isFilled;
    private ShapeType shapeType;
    private Shape shape;
    private Shape selectedShape;
    private int mode;

    public CanvasModel(List<Shape> shapes, Color color, Boolean isFilled, int mode) {
        this.shapes = shapes;
        this.selectedShape = null;
        this.color = color;
        this.isFilled = isFilled;
        this.mode = mode;
        undoList = new Stack<>();
        redoList = new Stack<>();
    }

    public CanvasModel() {
        this(new ArrayList<>(), Color.black, false, SELECT_MODE);
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            List<Shape> temp = shapes;
            redoList.push(temp);
            shapes = undoList.pop();
        }
    }

    public void selectShape(int x, int y) {
        selectedShape = null;
        for (int i = shapes.size() - 1; i >= 0 ; i--) {
            Shape shape = shapes.get(i);
            if (shape.contain(x, y)) {
                selectedShape = shape;
                return;
            }
        }
    }

    public void dragTo(int x, int y) {
        
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            List<Shape> temp = shapes;
            undoList.push(temp);
            shapes = redoList.pop();
        }
    }

    public void save(File toFile) throws IOException {
        addUndo();
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
    }

    public void createShape(int startPointX, int startPointY) {
        if (shapeType == null) {
            return;
        }
        if (shapeType == ShapeType.LINE) {
            shape = new Line(color, isFilled, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.RECTANGLE) {
            shape = new Rectangle(color, isFilled, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.ELLIPSE) {
            shape = new Ellipse(color, isFilled, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.DIAGONAL_CROSS) {
            shape = new DiagonalCross(color, isFilled, startPointX, startPointY, startPointX, startPointY);
        }
    }

    public void setEndPoint(int endPointX, int endPointY) {
        if (shape == null) {
            return;
        }
        shape.setEndPoint(endPointX, endPointY);
    }

    public void drawCurrentShape(Graphics g) {
        this.shape.draw((Graphics2D)g);
    }

    public void drawAllShapes(Graphics g) {
        for (Shape shape: shapes) {
            shape.draw((Graphics2D) g);
            System.out.println(shape.getColor());
        }
    }

    public void addUndo() {
        redoList.clear();
        List<Shape> temp = new ArrayList<>(shapes);
        undoList.push(temp);
    }

    public void finishedDrawing() {
        if (this.shape == null) {
            return;
        }
        Shape finishedShape = this.shape;
        addUndo();
        shapes.add(finishedShape);
        this.shape = null;
    }

    public void lockRatio(boolean lock) {
        if (shapeType == null && shape == null) {
            return;
        }
        if (shape instanceof  Lockable) {
            ((Lockable)shape).enableLock(lock);
        }
    }

    public void setColor(Color color) {
        Color oldColor = this.color;
        this.color = color;
        firePropertyChange("Color", oldColor, this.color);
    }

    public void setShapeType(ShapeType shapeType) {
        ShapeType oldType = this.shapeType;
        this.shapeType = shapeType;
        setMode(DRAW_MODE);
        firePropertyChange("ShapeType", oldType, this.shapeType);
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

    public void changeFilledState() {
        isFilled = !isFilled;
    }
}
