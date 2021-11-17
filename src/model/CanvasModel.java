/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 200011181
 * @version 1.0
 */
public class CanvasModel extends AbstractModel {
    private List<Shape> shapes;
    private Color color;
    private ShapeType shapeType;
    private Shape shape;

    public CanvasModel(List<Shape> shapes, Color color) {
        this.shapes = shapes;
        this.color = color;
    }

    public CanvasModel() {
        this(new ArrayList<>(), Color.black);
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

    public void load(File fromFile) throws IOException, ClassNotFoundException {
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
            shape = new Line(color, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.RECTANGLE) {
            shape = new Rectangle(color, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.ELLIPSE) {
            shape = new Ellipse(color, startPointX, startPointY, startPointX, startPointY);
        } else if (shapeType == ShapeType.DIAGONAL_CROSS) {
            shape = new DiagonalCross(color, startPointX, startPointY, startPointX, startPointY);
        }
    }

    public void setEndPoint(int endPointX, int endPointY) {
        if (shape == null) {
            return;
        }
        shape.setEndPoint(endPointX, endPointY);
    }

    public void drawCurrentShape(Graphics g) {
        this.shape.draw(g);
    }

    public void drawAllShapes(Graphics g) {
        for (Shape shape: shapes) {
            shape.draw(g);
            System.out.println(shape.getColor());
        }
    }

    public void finishedDrawing() {
        if (this.shape == null) {
            return;
        }
        Shape finishedShape = this.shape;
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
        firePropertyChange("ShapeType", oldType, this.shapeType);
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
}
