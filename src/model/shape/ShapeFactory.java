/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

import java.awt.*;
import java.lang.reflect.Constructor;

/**
 * @author 200011181
 * @version 1.0
 */
public class ShapeFactory {
    private static final String SHAPE_PACKAGE_ROOT = "model.shape.";

    private ShapeFactory() {
    }

    public static Shape createShape(ShapeType type, Color color, boolean isFilled,
                                    int startPointX, int startPointY, int endPointX, int endPointY) {
        Shape shape = null;
        try {
            Constructor constructor = Class.forName(SHAPE_PACKAGE_ROOT + type.getName()).getConstructor(Color.class,
                    boolean.class, int.class, int.class, int.class, int.class);
            shape = (Shape) constructor.newInstance(color, isFilled, startPointX, startPointY, endPointX, endPointY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shape;

    }


}
