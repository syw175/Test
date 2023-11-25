
package com.project.group13.backend.model.gameobjects;

/**
 * Represents an enemy in the Maze Runner game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface Enemy {

	public static int MAX_POWER = 10;

	/**
	 * Attacks on the player
	 * @param player player
	 */
	public abstract void attack(Player player);

	/**
	 * Gets power of the enemy
	 * @return power of the enemy
	 */
	public abstract int getPower();

}