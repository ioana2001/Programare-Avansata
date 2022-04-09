package compulsory;

import java.util.List;
import java.util.logging.Logger;

public class Player implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String name;
    private Game game;
    private boolean running = true;

    public Player(String name) {
        this.name = name;
    }

    /**
     * adds the word to the board
     */
    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty()) {
            return false;
        }
        // create a word with all the extracted tiles;
        StringBuilder word = new StringBuilder();
        for (Tile tile : extracted)
            word.append(tile.getLetter());
        game.getBoard().addWord(this, word.toString());
        // make the player sleep 50 ms;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            LOGGER.warning("interrupted exception");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void run() {
        while (running) {
            running = this.submitWord();
        }
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
