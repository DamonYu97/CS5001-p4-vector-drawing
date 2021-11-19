/*
 * Copyright 2021 Damon Yu
 */
package delegate;

import model.CanvasModel;
import model.shape.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private List<JButton> shapeButtons;
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
        setSize(700, 700);
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
        setupShapeButtons();
        JButton fillButton = new JButton("Fill");
        colourButton = new JButton();
        colourButton.setBackground(Color.black);
        JButton redo = new JButton("Redo");
        JButton undo = new JButton("Undo");
        JButton select = new JButton("Select");
        toolBar.add(colourButton);
        toolBar.add(fillButton);
        toolBar.add(undo);
        toolBar.add(redo);
        toolBar.add(select);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        colourButton.addActionListener(e -> {
            Color colour = JColorChooser.showDialog(this, "Colour Selection", canvasModel.getColor());
            canvasModel.setColor(colour);
            colourButton.setBackground(colour);
            if (canvasModel.getMode() == CanvasModel.SELECT_MODE && canvasModel.getSelectedShape() != null) {
                canvasModel.updateSelectedShapeColor();
                canvasPanel.repaint();
            }
        });

        fillButton.addActionListener(e -> {
            canvasModel.changeFilledState();
            if (canvasModel.getMode() == CanvasModel.SELECT_MODE && canvasModel.getSelectedShape() != null) {
                canvasModel.changeSelectedFilledState();
                canvasPanel.repaint();
            }
        });

        undo.addActionListener(e -> {
            canvasModel.undo();
            canvasPanel.repaint();
        });

        redo.addActionListener(e -> {
            canvasModel.redo();
            canvasPanel.repaint();
        });

        select.addActionListener(e -> {
            canvasModel.setMode(CanvasModel.SELECT_MODE);
        });
    }

    private void setupShapeButtons() {
        shapeButtons = new ArrayList<>();
        AbstractAction shapeButtonActionListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set the shape type of canvas model based on the button name
                ShapeType type = ShapeType.typeOfName(e.getActionCommand());
                canvasModel.setShapeType(type);
            }
        };
        //create shape button for each type of shape
        for (ShapeType type : ShapeType.values()) {
            //set up the button name based on the shape type
            JButton shapeButton = new JButton(type.getName());
            shapeButtons.add(shapeButton);
            toolBar.add(shapeButton);
            shapeButton.addActionListener(shapeButtonActionListener);
        }
    }

}
