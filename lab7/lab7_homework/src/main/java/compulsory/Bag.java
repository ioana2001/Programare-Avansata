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
     * adds to tiles: A-9, B-2, C-2, D-4, E-12, F-2, G-3, H-2, I-9, J-1,
     * K-1, L-4, M-2, N-6, O-8, P-2, Q-1, R-6, S-4, T-6, U-4, V-2, W-2, X-1, Y-2, Z-1
     */
    public Bag() {
        tiles = new ArrayList<>();
        for (int index = 0; index < 12; index++) {
            tiles.add(new Tile('e', 1));
        }
        for (int index = 0; index < 9; index++) {
            tiles.add(new Tile('a', 1));
            tiles.add(new Tile('i', 1));
        }
        for (int index = 0; index < 8; index++) {
            tiles.add(new Tile('o', 1));
        }
        for (int index = 0; index < 6; index++) {
            tiles.add(new Tile('r', 1));
            tiles.add(new Tile('t', 1));
            tiles.add(new Tile('n', 1));
        }
        for (int index = 0; index < 4; index++) {
            tiles.add(new Tile('l', 1));
            tiles.add(new Tile('u', 1));
            tiles.add(new Tile('d', 2));
            tiles.add(new Tile('s', 1));
        }
        for (int index = 0; index < 3; index++) {
            tiles.add(new Tile('g', 2));
        }
        for (int index = 0; index < 4; index++) {
            tiles.add(new Tile('b', 3));
            tiles.add(new Tile('c', 3));
            tiles.add(new Tile('f', 4));
            tiles.add(new Tile('h', 4));
            tiles.add(new Tile('m', 3));
            tiles.add(new Tile('p', 3));
            tiles.add(new Tile('v', 4));
            tiles.add(new Tile('w', 4));
            tiles.add(new Tile('y', 4));
        }
        tiles.add(new Tile('j', 8));
        tiles.add(new Tile('k', 5));
        tiles.add(new Tile('q', 10));
        tiles.add(new Tile('x', 8));
        tiles.add(new Tile('z', 10));

    }

    /**
     * extracts tiles from the bag
     *
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