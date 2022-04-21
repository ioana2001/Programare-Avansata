package compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * contains the players, board and bag
 */
public class Game {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();
    private ArrayList<Thread> threads = new ArrayList<>();
    private int nrPlayers = 3;

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
        game.printScores();
    }

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
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.warning("exception at joining threads");
                e.printStackTrace();
            }
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void printScores() {
        System.out.println();
        int maxScoreIndex = 0;
        for (Player player : players) {
            int score = player.getScore();
            System.out.println(player.getName() + " has score: " + score);
            if (score > players.get(maxScoreIndex).getScore())
                maxScoreIndex = players.indexOf(player);
        }

        System.out.println("The winner is: " + players.get(maxScoreIndex).getName());
    }

    int getNrPlayers() {
        return nrPlayers;
    }

    int getIndexOfPlayer(String name) {
        int index = 0;
        for (Player player : players)
            if (player.getName().equals(name))
                return index;
            else index++;
        return index;
    }

    Thread getNextThread(String name) {
        int index = getIndexOfPlayer(name);
        if (index == nrPlayers - 1)
            return threads.get(0);
        return threads.get(index + 1);
    }

    boolean isFirstPlayer(String name) {
        return name.equals(players.get(0).getName());
    }
}
