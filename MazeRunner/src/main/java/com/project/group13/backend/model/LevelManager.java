
package com.project.group13.backend.model;

import com.project.group13.backend.model.levels.*;

/**
 * The LevelManager class is responsible for managing the different levels in the game.
 * It tracks the current level, the maximum level available in the game, and updates the game
 * model with the appropriate map for the current level. This class allows for advancing,
 * regressing, and resetting levels within the game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LevelManager {

    private int level;
    private Level levelMap;
    private GameModel model;
    public static final int MAX_LEVEL = 3;

    /**
     * Constructs level manager
     */
    public LevelManager(GameModel model) {
        this.model = model;
        this.level = 1; // default level 1
        this.updateMap();
    }

    /************************ Getter/Setter ************************/

    /**
     * Updates map according to level
     */
    private void updateMap() {
        switch (level) {
            case 2 -> {
                this.levelMap = new Level2();
                this.levelMap.initModels(model);
            }
            case 3 -> {
                this.levelMap = new Level3();
                this.levelMap.initModels(model);
            }
            default -> {
                this.levelMap = new Level1();
                this.levelMap.initModels(model);
            }
        }
    }

    /**
     * Checks if next level is available
     * @return true if available otherwise false
     */
    public boolean nextLevelAvailaible() {
        return level < LevelManager.MAX_LEVEL;
    }

    /**
     * To next level
     */
    public void nextLevel() {
        level++;
        updateMap();
    }

    /**
     * To previous level
     */
    public void previousLevel() {
        level--;
        updateMap();
    }

    /**
     * Gets level in the game
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Resets level
     */
    public void resetLevel() {
        this.level = 1;
        updateMap();
    }

    /**
     * Get current level map
     * @return level map
     */
    public Level getLevelMap() {
        return levelMap;
    }

}
