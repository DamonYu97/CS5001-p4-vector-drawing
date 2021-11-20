package model;

import java.util.ArrayList;
import java.util.List;

/**
 * General Mode class initializes the basic observer behaviour with list of ShapeListeners.
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
