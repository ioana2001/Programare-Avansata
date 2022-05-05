package map;

import DAO.CitiesDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * class for drawing the points representing the cities
 */
public class Points extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);

        Dimension size = getSize();

        int w = size.width;
        int h = size.height;

        var cities = new CitiesDAO();
        List<Integer> ids = cities.idCities();
        for (Integer id : ids) {
            double x = 0;
            double y = 0;
            try {
                SphericalMercator sphericalMercator = new SphericalMercator();
                x = sphericalMercator.xAxisProjection(cities.getLatitude(id)) % w;
                y = sphericalMercator.yAxisProjection(cities.getLongitude(id)) % h;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            g2d.draw(new Ellipse2D.Double(x, y, 3, 3));
        }
    }

}