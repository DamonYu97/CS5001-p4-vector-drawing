package delegate;

import model.CanvasModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 200011181
 * @version 1.0
 */
public class CanvasPanel extends JPanel {
    private CanvasModel model;
    private Point dragStartPoint;
    private Point initStart;
    private Point initEnd;

    public CanvasPanel(CanvasModel model) {
        this.model = model;
        dragStartPoint = null;
        initStart = null;
        initEnd = null;
        setBackground(Color.white);
        MyMouseAdapter listener = new MyMouseAdapter();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            //System.out.println("Click");
            if (model.getMode() == CanvasModel.SELECT_MODE) {
                model.selectShape(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            //System.out.println("Released");
            dragStartPoint = null;
            initStart = null;
            initEnd = null;
            model.finishedDrawing();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (model.getMode() == CanvasModel.DRAW_MODE) {
                //if it is draw mode
                if (model.getShape() == null) {
                    //create a shape when starting drawing.
                    model.createShape(e.getX(), e.getY());
                } else {
                    //change the endpoint when drawing.
                    model.setEndPoint(e.getX(), e.getY());
                    //System.out.println("Dragged " + model.getShape());
                }
            } else if (model.getMode() == CanvasModel.SELECT_MODE) {
                if (dragStartPoint == null) {
                    //if just start drag, then first find the shape where your mouse put on.
                    model.selectShape(e.getX(), e.getY());
                    if (model.getSelectedShape()!= null) {
                        //initialize data for drag
                        dragStartPoint = new Point(e.getX(), e.getY());
                        initStart = model.getSelectedShape().getStartPoint();
                        initEnd = model.getSelectedShape().getEndPoint();
                        model.startDrag();
                    }
                } else {
                    if (model.getSelectedShape() != null) {
                        //move the selected shape
                        int offsetX = e.getX() - dragStartPoint.x;
                        int offsetY = e.getY() - dragStartPoint.y;
                        model.drag(offsetX, offsetY, initStart, initEnd);
                    }
                }
            }


        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model.getShape() != null) {
            model.drawCurrentShape(g);
        }
        if (model.getShapes().size() > 0) {
            model.drawAllShapes(g);
        }
    }

}
