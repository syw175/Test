package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;

/**
 * Moving Barriers object (It will stop player from entering in specific locations)
 */
public class SpecialBarrier extends AutoMovingObject implements Barrier {

    /**
     * Constructs a moving game object with a specified position
     * @param coordinate The position of the game object
     * @param orientation object orientation
     * @param movementRange The movement range of the player
     * @param movementAxis The movement axis of the player
     * @param movementSpeed The speed of the movement of this object
     */
    public SpecialBarrier(Position coordinate, Directions orientation, int movementRange, Axis movementAxis, double movementSpeed) {
        super(coordinate, orientation, movementRange, movementAxis, movementSpeed);
    }

    /************************** Functions **************************/

    /**
     * Damages the player and removes 100HP health
     * Update it as you want in the subclass, by extending this
     * @param player player
     */
    @Override
    public void damage(Player player) {
        player.damage(100);
    }

}