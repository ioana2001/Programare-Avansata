package compulsory;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame contains:
 *  - configuration panel that contains 2 input boxes for the number of rows and columns;
 *  - the main panel that contains the grid of the game;
 *  - control panel that contains 2 buttons for loading and saving;
 */
public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.lightGray);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel();
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }

    /**
     * this method is used when we are pressing the "Create"
     * button to resize the grid
     */
    public void resetFrame() {
        remove(canvas);
        canvas = new DrawingPanel(this);
        this.add(canvas);
        pack();
        this.setVisible(true);
    }


}
