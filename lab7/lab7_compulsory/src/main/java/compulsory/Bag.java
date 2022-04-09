package compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bag represents a collection of tiles
 * from which the player extracts letters
 */
public class Bag {
    private final ArrayList<Tile> tiles; //declare a collection for tiles;

    /**
     * adds to tiles 10 letters of each
     */
    public Bag() {
        tiles = new ArrayList<>();
        for (char c = 'a'; c < 'z'; c++) {
            Random rand = new Random();
            int points = 1 + rand.nextInt(10);
            for (int index = 0; index < 10; index++) {
                tiles.add(new Tile(c, points));
            }
        }
    }

    /**
     * extracts tiles from the bag
     * @param howMany : represents the number of
     *                tiles we want to extract
     * @return : a list with the extracted tiles
     */
    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tiles.isEmpty()) {
                break;
            }
            // poll one tile from the collection
            Random rand = new Random();
            int letterIndex = rand.nextInt(tiles.size());
            extracted.add(tiles.get(letterIndex));
            tiles.remove(letterIndex);
        }
        return extracted;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}