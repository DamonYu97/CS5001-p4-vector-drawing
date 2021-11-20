package delegate;

import model.CanvasModel;
import model.ShapeListener;
import model.shape.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 200011181
 * @version 1.0
 */
public class VectorDrawingDelegate extends JFrame implements ShapeListener {
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private CanvasPanel canvasPanel;

    private JMenu file;
    private JMenuItem save;
    private JMenuItem load;

    private List<JButton> shapeButtons;

    private CanvasModel canvasModel;

    public VectorDrawingDelegate(CanvasModel model) {
        super("Vector Drawing Program");
        canvasModel = model;
        model.addListener(this);
        setLayout(new BorderLayout());
        setupMenuBar();
        setupToolBar();
        canvasPanel = new CanvasPanel(canvasModel);
        getContentPane().add(canvasPanel, BorderLayout.CENTER);
        setSize(1200, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //System.out.println("key typed");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                if (canvasModel.getMode() == CanvasModel.DRAW_MODE) {
                    if (e.getExtendedKeyCode() == KeyEvent.VK_SHIFT) {
                        canvasModel.lockRatio(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e);
                if (canvasModel.getMode() == CanvasModel.DRAW_MODE) {
                    if (e.getExtendedKeyCode() == KeyEvent.VK_SHIFT) {
                        canvasModel.lockRatio(false);
                    }
                }
            }
        });
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
        JButton colourButton = new JButton();
        colourButton.setBackground(Color.black);
        JButton redoButton = new JButton("Redo");
        JButton undoButton = new JButton("Undo");
        JButton selectButton = new JButton("Select");
        JButton clearButton = new JButton("Clear");
        toolBar.add(colourButton);
        toolBar.add(fillButton);
        toolBar.add(undoButton);
        toolBar.add(redoButton);
        toolBar.add(selectButton);
        toolBar.add(clearButton);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        colourButton.addActionListener(e -> {
            Color colour = JColorChooser.showDialog(this, "Colour Selection", canvasModel.getColor());
            canvasModel.setColor(colour);
            colourButton.setBackground(colour);
            if (canvasModel.getMode() == CanvasModel.SELECT_MODE && canvasModel.getSelectedShape() != null) {
                canvasModel.updateSelectedShapeColor();
            }
        });

        fillButton.addActionListener(e -> {
            canvasModel.changeFilledState();
            if (canvasModel.getMode() == CanvasModel.SELECT_MODE && canvasModel.getSelectedShape() != null) {
                canvasModel.changeSelectedFilledState();
            }
        });

        undoButton.addActionListener(e -> {
            canvasModel.undo();
        });

        redoButton.addActionListener(e -> {
            canvasModel.redo();
        });

        selectButton.addActionListener(e -> {
            canvasModel.setMode(CanvasModel.SELECT_MODE);
        });

        clearButton.addActionListener(e -> {
            canvasModel.cleanup();
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
                requestFocus();
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

    @Override
    public void update() {
        canvasPanel.repaint();
    }
}
