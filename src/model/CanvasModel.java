/*
 * Copyright 2021 Damon Yu
 */
package model;

import Util.ListUtil;
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
 * @author 200011181
 * @version 1.0
 */
public class CanvasModel extends GeneralModel {
    public static final int SELECT_MODE = 0;
    public static final int DRAW_MODE = 1;
    private List<Shape> shapes;
    private Stack<byte[]> undoList;
    private Stack<byte[]> redoList;
    private Color color;
    private boolean isFilled;
    private ShapeType shapeType;
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
        this(new ArrayList<>(), Color.black, false, SELECT_MODE);
    }


    public void undo() {
        if (!undoList.isEmpty()) {
            try {
                redoList.push(listUtil.serialize(shapes));
                shapes = listUtil.deserialize(undoList.pop());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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

    public void drag(int offSetX, int offsetY, Point initStart, Point initEnd) {
        if (selectedShape != null) {
            selectedShape.move(offSetX, offsetY, initStart, initEnd);
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            try {
                undoList.push(listUtil.serialize(shapes));
                shapes = listUtil.deserialize(redoList.pop());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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
        shape = ShapeFactory.createShape(shapeType, color, isFilled,
                startPointX, startPointY, startPointX, startPointY);
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
        }
    }

    private void addUndo() {
        redoList.clear();
        try {
            undoList.push(listUtil.serialize(shapes));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void lockRatio(boolean lock) {
        if (shapeType == null && shape == null) {
            return;
        }
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
        }
    }

    public void setShapeType(ShapeType shapeType) {
        ShapeType oldType = this.shapeType;
        this.shapeType = shapeType;
        setMode(DRAW_MODE);
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

    public void changeSelectedFilledState() {
        if (selectedShape != null) {
            addUndo();
            selectedShape.setFilled(!selectedShape.getFilled());
        }
    }
}
