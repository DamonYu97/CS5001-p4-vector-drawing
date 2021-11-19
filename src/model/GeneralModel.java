/*
 * Copyright 2021 Damon Yu
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 200011181
 * @version 1.0
 */
public class GeneralModel {

    protected List<ShapeListener> shapeListeners;

    public GeneralModel()
    {
        shapeListeners = new ArrayList<>();
    }

    public void addListener(ShapeListener listener) {
        shapeListeners.add(listener);
    }

    protected void changed() {
        for (ShapeListener listener : shapeListeners) {
            listener.update();
        }
    }

}
