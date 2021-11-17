/*
 * Copyright 2021 Damon Yu
 */
package delegate;

import model.CanvasModel;
import model.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * @author 200011181
 * @version 1.0
 */
public class VectorDrawingDelegate extends JFrame {
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private CanvasPanel canvasPanel;

    private JMenu file;
    private JMenuItem save;
    private JMenuItem load;

    private JButton lineButton;
    private JButton rectangleButton;
    private JButton ellipseButton;
    private JButton diagonalCrossButton;
    private JButton colourButton;

    private CanvasModel canvasModel;

    public VectorDrawingDelegate(CanvasModel model) {
        super("Vector Drawing Program");
        canvasModel = model;
        setLayout(new BorderLayout());
        setupMenuBar();
        setupToolBar();
        canvasPanel = new CanvasPanel(canvasModel);
        getContentPane().add(canvasPanel, BorderLayout.CENTER);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void setupMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        file.add(save);
        file.add(load);
        menuBar.add(file);
        setJMenuBar(menuBar);
        save.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            int option = jFileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    canvasModel.save(jFileChooser.getSelectedFile());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        load.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            int option = jFileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    canvasModel.load(jFileChooser.getSelectedFile());
                    repaint();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setupToolBar() {
        toolBar = new JToolBar();
        toolBar.setAlignmentX(0);
        toolBar.setLayout(new GridLayout(1,2));
        lineButton = new JButton("Line");
        rectangleButton = new JButton("Rectangle");
        ellipseButton = new JButton("Ellipse");
        diagonalCrossButton = new JButton("Cross");
        colourButton = new JButton();
        colourButton.setBackground(Color.black);
        toolBar.add(lineButton);
        toolBar.add(rectangleButton);
        toolBar.add(ellipseButton);
        toolBar.add(diagonalCrossButton);
        toolBar.add(colourButton);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        lineButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasModel.setShapeType(ShapeType.LINE);
            }
        });

        rectangleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasModel.setShapeType(ShapeType.RECTANGLE);
            }
        });

        ellipseButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasModel.setShapeType(ShapeType.ELLIPSE);
            }
        });

        diagonalCrossButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasModel.setShapeType(ShapeType.DIAGONAL_CROSS);
            }
        });

        colourButton.addActionListener(e -> {
            Color colour = JColorChooser.showDialog(this, "Colour Selection", canvasModel.getColor());
            canvasModel.setColor(colour);
            colourButton.setBackground(colour);
        });
    }

}
