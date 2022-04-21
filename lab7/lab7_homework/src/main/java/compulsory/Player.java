package compulsory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Player implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String name;
    private Game game;
    private int playerPoints = 0;
    private boolean running = true;
    private List<Tile> extracted;

    public Player(String name) {
        this.name = name;
    }

    /**
     * adds the word to the board
     */
    private boolean submitWord() {
        if (extracted.isEmpty()) {
            return false;
        }
        // create a word with all the extracted tiles;
        int wordPoints = generateWord();
        playerPoints += wordPoints;
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
        extracted = game.getBag().extractTiles(7);
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

    /**
     * generates a word with its associated score
     *
     * @return : the score of the word submitted
     */
    public int generateWord() {
        if (extracted.size() < 3) {
            extracted.clear();
            game.getBoard().addWord(this, "player finished tiles!");
            return 0;
        }
        for (int attempt = 0; attempt < 100; attempt++) {
            Random rand = new Random();
            Collections.shuffle(extracted, rand);
            for (int wordLength = 7; wordLength > 2; wordLength--) {
                StringBuilder word = new StringBuilder();
                int wordLengthCopy = wordLength;
                ArrayList<Tile> extractedTiles = new ArrayList<>();
                int points = 0;
                for (Tile tile : extracted) {
                    word.append(tile.getLetter());
                    extractedTiles.add(tile);
                    points += tile.getPoints();
                    wordLengthCopy--;
                    if (wordLengthCopy == 0)
                        break;
                }
                points *= wordLength;
                if (game.getDictionary().isWord(String.valueOf(word))) {
                    game.getBoard().addWord(this, word.toString());
                    for (Tile tile : extractedTiles)
                        extracted.remove(tile);
                    extracted.addAll(game.getBag().extractTiles(wordLength));
                    return points;
                }
            }
        }

        game.getBoard().addWord(this, "no word can be made!");
        extracted.remove(extracted.get(0));
        extracted.addAll(game.getBag().extractTiles(1));
        return 0;
    }

    public int getScore() {
        return playerPoints;
    }
}
