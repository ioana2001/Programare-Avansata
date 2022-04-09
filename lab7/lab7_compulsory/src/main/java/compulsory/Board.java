package compulsory;

import java.util.ArrayList;

/**
 * prints the words and the player that submits it
 */
public class Board {
    private final ArrayList<String> words;

    public Board() {
        words = new ArrayList<>();
    }

    /**
     * adds a word to the board
     */
    public synchronized void addWord(Player player, String word) {
        words.add(word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        return words.toString();
    }


}