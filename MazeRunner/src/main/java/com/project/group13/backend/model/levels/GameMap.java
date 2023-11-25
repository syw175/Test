
package com.project.group13.backend.model.levels;

/**
 * Holds map data and provides functionality to read and write on it
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class GameMap {

    private int[][] gameMap;

    /**
     * constructs game map
     */
    public GameMap(int[][] gameMap) {
        this.gameMap = gameMap;
    }

    /********************** Functions / Getter / Setter ***********************/

    /**
     * Returns the block type situated on the given location
     * @param row row index
     * @param col column index
     * @return block type
     */
    public BlockType getBlockAt(int row, int col) {
        return BlockType.values()[gameMap[row][col]];
    }

    /**
     * Returns the size of row and column of the map
     * @return size of the map
     */
    public int[] getSize() {
        return new int[]{gameMap.length, gameMap[0].length};
    }


}