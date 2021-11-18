/*
 * Copyright 2021 Damon Yu
 */
package delegate;

import model.CanvasModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        setFocusable(true);
        MyMouseAdapter listener = new MyMouseAdapter();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    model.lockRatio(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    model.lockRatio(false);
                }
            }
        });
    }

    class MyMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (model.getMode() == CanvasModel.SELECT_MODE) {
                model.selectShape(e.getX(), e.getY());
            }
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
            if (model.getMode() == CanvasModel.DRAW_MODE) {
                if (model.getShape() == null) {
                    model.createShape(e.getX(), e.getY());
                } else {
                    model.setEndPoint(e.getX(), e.getY());
                    System.out.println("Dragged " + model.getShape());
                    repaint();
                }
            } else if (model.getMode() == CanvasModel.SELECT_MODE) {
                System.out.println("Move shape");
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
