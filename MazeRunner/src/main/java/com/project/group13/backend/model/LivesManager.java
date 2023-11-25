
package com.project.group13.backend.model;

/**
 * LivesManager is responsible for managing the player's lives within the game. It tracks the current
 * number of lives and provides methods to increment or decrement the life count. This manager is used
 * to update the game state in response to player deaths or collecting life bonuses.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LivesManager {

    private int lives;

    /**
     * Constructs lives manager
     * @param baseLives base life count
     */
    public LivesManager(int baseLives) {
        this.lives = baseLives;
    }

    /************************ Getter/Setter ************************/

    /**
     * Increase live counter by 1
     */
    public void addLife() {
        lives++;
    }

    /**
     * Reduce live counter by 1
     */
    public void removeLife() {
        lives--;
    }

    /**
     * Return the number of remaining lives 
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets number of lives in the game
     * @param lives number of life
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

}
