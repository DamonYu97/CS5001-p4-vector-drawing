package main;

import delegate.VectorDrawingDelegate;
import model.CanvasModel;

/**
 * @author 200011181
 * @version 1.0
 */
public class VectorDrawingMain {
    public static void main(String[] args) {
        CanvasModel model = new CanvasModel();
        VectorDrawingDelegate view = new VectorDrawingDelegate(model);
    }
}
