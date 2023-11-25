
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;

/**
 * Represents a special moving enemy in the game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SpecialEnemy extends AutoMovingObject implements Enemy {

    private final int power;

    /**
     * Constructs a moving game object with a specified position
     * @param coordinate The position of the game object
     * @param orientation object orientation
     * @param movementRange The movement range of the player
     * @param movementAxis The movement axis of the player
     * @param movementSpeed The speed of the movement of this object
     * @param power power of the enemy
     */
    public SpecialEnemy(Position coordinate, Directions orientation, int movementRange, Axis movementAxis, double movementSpeed, int power) {
        super(coordinate, orientation, movementRange, movementAxis, movementSpeed);
        this.power = power;
    }

    /************************** Functions **************************/

    /**
     * Default attack : removes a life on collision
     * Update it as you want in the subclass, by extending this
     * @param player player
     */
    @Override
    public void attack(Player player) {
        player.damage(100);
    }

    /**
     * gets current power value
     * @return power
     */
    @Override
    public int getPower() {
        return power;
    }

}