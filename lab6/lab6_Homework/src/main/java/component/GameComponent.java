package component;

import component.Stick;
import component.Stone;
import panels.DrawingPanel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * used for storing the game data (position of sticks and stones)
 */
public class GameComponent {

    private DrawingPanel drawingPanel;

    private ArrayList<Stick> sticks = new ArrayList<>();
    private ArrayList<Stone> stones = new ArrayList<>();

    // used for remembering the position of the last stone placed
    private Stone lastStone;
    // used for checking if the current round is the first one
    private boolean firstRound;

    public GameComponent(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        lastStone = new Stone(-1, -1);
        firstRound = true;
    }

    /**
     * draws sticks on the table at random locations
     */
    public void drawRandomSticks(Graphics2D g, int rows, int cols) {

        for (int row = 0; row < rows * rows / 3; row++) {
            int randomRow = (int) (Math.random() * (rows - 1));
            int randomCol = (int) (Math.random() * cols);
            Stick stick = new Stick(randomCol, randomRow, randomCol, randomRow + 1);
            boolean ok = true;

            for (Stick element : sticks)
                if (element.equals(stick)) {
                    row--;
                    ok = false;
                }

            if (ok) {
                drawingPanel.drawStick(g, randomCol, randomRow, randomCol, randomRow + 1);
                sticks.add(stick);
            }
        }

        for (int col = 0; col < cols * cols / 3; col++) {
            int randomRow = (int) (Math.random() * rows);
            int randomCol = (int) (Math.random() * (cols - 1));

            Stick stick = new Stick(randomCol, randomRow, randomCol + 1, randomRow);

            boolean ok = true;

            for (Stick element : sticks)
                if (element.equals(stick)) {
                    col--;
                    ok = false;
                    break;
                }

            if (ok) {
                drawingPanel.drawStick(g, randomCol, randomRow, randomCol + 1, randomRow);
                sticks.add(stick);
            }
        }
    }

    public void addStone(Stone stone) {
        stones.add(stone);
        updateLastStone(stone);
    }

    /**
     * stores the current state of the game (sticks and stones locations)
     * in sticks.json and stones.json
     */
    public void serialize() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("saved_game/sticks.json"), sticks);
            //String sticksJson = mapper.writeValueAsString(sticks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("saved_game/stones.json"), stones);
            //String stonesJson = mapper.writeValueAsString(stones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateLastStone(Stone stone) {
        lastStone = stone;
    }

    /**
     * checks if stone position is valid according to the game rules
     * (a stone is valid if there is no stone there and is adjacent with the last stone placed)
     */
    public boolean validStone(Stone stone) {
        if (firstRound) {
            firstRound = false;
            return true;
        }

        for (Stone element : stones)
            if (stone.equals(element))
                return false;

        Stick fromStoneToLastStone = new Stick(stone.getX(), stone.getY(), lastStone.getX(), lastStone.getY());

        for (Stick element : sticks) {
            if (fromStoneToLastStone.equals(element))
                return true;
        }

        return false;
    }

    /**
     * checks if the game ended
     */
    public boolean isWinner(Stone stone) {

        int x = stone.getX();
        int y = stone.getY();

        Stick stickLeft = new Stick(x, y, x, y - 1);
        Stick stickRight = new Stick(x, y, x, y + 1);
        Stick stickTop = new Stick(x, y, x - 1, y);
        Stick stickBottom = new Stick(x, y, x + 1, y);

        for (Stick element : sticks) {
            if (stickLeft.equals(element) && !placedStone(new Stone(x, y - 1)))
                return false;
            if (stickRight.equals(element) && !placedStone(new Stone(x, y + 1)))
                return false;
            if (stickTop.equals(element) && !placedStone(new Stone(x - 1, y)))
                return false;
            if (stickBottom.equals(element) && !placedStone(new Stone(x + 1, y)))
                return false;
        }

        return true;
    }

    /**
     * checks if there is a stone at the coordinates of the stone parameter
     */
    boolean placedStone(Stone stone) {
        for(Stone element:stones)
            if(stone.equals(element))
                return true;
        return false;
    }

}
