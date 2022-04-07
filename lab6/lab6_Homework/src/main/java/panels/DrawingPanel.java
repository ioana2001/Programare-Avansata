package panels;

import component.GameComponent;
import homework.MainFrame;
import component.Stone;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.*;

public class DrawingPanel extends JPanel implements MouseListener {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final MainFrame frame;
    public GameComponent game;
    private int rows, cols;
    private int canvasWidth, canvasHeight;
    private int boardWidth, boardHeight;
    private int cellWidth, cellHeight;
    final static int stoneSize = 20;

    // padX represents the right and left padding and
    // padY represents the top and left padding for the canvas
    private int padX, padY;

    // true - reds turn
    // false - blues turn
    boolean round = true;


    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        addMouseListener(this);
        game = new GameComponent(this);
        rows = frame.configPanel.getRows();
        cols = frame.configPanel.getCols();
        init(rows, cols);
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        canvasWidth = cols * 60;
        canvasHeight = rows * 60;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    /**
     * Used for creating the main grid
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        game.drawRandomSticks(g, rows, cols);
    }

    /**
     * creates the lines and nodes for the main grid
     */
    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }
        //vertical lines
        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }
        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    /**
     * draws a thicker line from node [x1, y1] to [x2, y2]
     */
    public void drawStick(Graphics2D g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.drawLine(padX + cellWidth * x1, padY + cellHeight * y1,
                padX + cellWidth * x2, padY + cellHeight * y2);
    }

    /**
     * draws a stone at coordinates x, y, on the canvas using the color "color"
     * x – the x coordinate of the upper left corner of the oval to be filled.
     * y – the y coordinate of the upper left corner of the oval to be filled.
     */
    void drawStone(Graphics2D g, int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x, y, stoneSize, stoneSize);
    }

    /**
     * checks if a stone is valid and places the stones
     * also checks if the game has ended and outputs the winner
     */
    public void mouseClicked(MouseEvent e) {
        Graphics2D g = (Graphics2D) getGraphics();

        int row = e.getX() / cellWidth;
        int col = e.getY() / cellHeight;

        Stone stone = new Stone(row, col);

        // center stone coordinates: padX + row * cellWidth, padY + col * cellHeight
        int x = padX + row * cellWidth - stoneSize / 2;
        int y = padY + col * cellHeight - stoneSize / 2;

        // check if the mouse is within a circle
        if (e.getX() >= x && e.getX() <= x + stoneSize
                && e.getY() >= y && e.getY() <= y + stoneSize
                && game.validStone(stone)) {
            Color color = Color.blue;
            if (round) {
                round = false;
                color = Color.red;
            } else {
                round = true;
            }
            drawStone(g, x, y, color);
            game.addStone(stone);
            if (game.isWinner(stone))
                if (round)
                    LOGGER.info("red is Winner");
                else LOGGER.info("blue is Winner");

        }
    }

    /**
     * used in Control Panel for making the screenshot
     */
    public void screenshot() {
        Dimension dimension = new Dimension(this.getWidth(), this.getHeight());
        Point i = this.getLocationOnScreen();
        BufferedImage image;
        File file = new File("img/screenshot.png");
        try {
            image = new Robot().createScreenCapture(new Rectangle(i, dimension));
            ImageIO.write(image, "PNG", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}


