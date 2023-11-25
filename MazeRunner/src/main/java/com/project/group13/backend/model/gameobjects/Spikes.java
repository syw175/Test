
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;

/**
 * Moving enemy object spikes
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Spikes extends SpecialBarrier {

    /**
     * Constructs a moving game object with a specified position.
     * @param coordinate The position of the game object.
     * @param movementRange range of movement
     * @param movementAxis movement axis
     * @param orientation spike orientation
     * @param movementSpeed speed of the movement of this object
     */
    public Spikes(Position coordinate, int movementRange, Axis movementAxis, Directions orientation, double movementSpeed) {
        super(coordinate, orientation, movementRange, movementAxis, movementSpeed);
    }
}
