/*
 * Copyright 2021 Damon Yu
 */
package model.shape;

/**
 * @author 200011181
 * @version 1.0
 */
public enum ShapeType {
    LINE("Line"),
    RECTANGLE("Rectangle"),
    ELLIPSE("Ellipse"),
    CROSS("Cross"),
    TRIANGLE("Triangle");

    private final String name;

    ShapeType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static ShapeType typeOfName(String name) {
        for (ShapeType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
