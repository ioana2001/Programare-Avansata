package map;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * class for drawing a map with cities points
 */
public class DrawMap {
    public void draw() throws SQLException {
        Points points = new Points();
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(points);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
