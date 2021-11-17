/*
 * Copyright 2021 Damon Yu
 */
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

    public CanvasPanel(CanvasModel model) {
        this.model = model;
        setBackground(Color.white);
        MyMouseAdapter listener = new MyMouseAdapter();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Pressed");
            model.createShape(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.out.println("Released");
            model.finishedDrawing();
            repaint();
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (model.getShape() == null) {
                model.createShape(e.getX(), e.getY());
            } else {
                model.setEndPoint(e.getX(), e.getY());
                System.out.println("Dragged " + model.getShape());
                repaint();
            }
        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("drawing");
        if (model.getShape() != null) {
            model.drawCurrentShape(g);
        }
        if (model.getShapes().size() > 0) {
            model.drawAllShapes(g);
        }
    }

}
