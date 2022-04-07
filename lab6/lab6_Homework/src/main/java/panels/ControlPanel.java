package panels;

import homework.MainFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Control Panel contains the buttons save and screenshot
 *  - save: saves the state of the game (the sticks and stones coordinates) in a json file
 *  - screenshot: does a screenshot of the board
 */
public class ControlPanel extends JPanel {

    public ControlPanel(MainFrame frame) {
        JButton screenshot = new JButton("Screenshot");
        JButton save = new JButton("Save");

        add(screenshot);
        add(save);

        screenshot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.canvas.screenshot();
            }
        });

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.canvas.game.serialize();
            }
        });
    }

}
