
package com.project.group13.backend.model.gameobjects;

/**
 * Represents barriers in the game
 */
public interface Barrier {
    /**
     * Damages the player
     * @param player player
     */
    public abstract void damage(Player player);
}
