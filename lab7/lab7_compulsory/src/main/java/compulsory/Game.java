package compulsory;

import java.util.ArrayList;
import java.util.List;

/**
 * contains the players, board and bag
 */
public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    /**
     * starting a thread for each player
     */
    public void play() {
        for (Player player : players) {
            // start a new Thread representing the player;
            Thread thread = new Thread(player);
            thread.start();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }


}
