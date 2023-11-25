package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.levels.Level;

/**
 * Can be used for moving enemies
 */
public class AutoMovingObject extends MovingObject {

    private Directions orientation;
    private final int movementRange;
    private final Axis movementAxis;
    private Directions defaultDir;
    private final double movementSpeed;
    private double lastUpdate;

    /**
     * Constructs a moving game object with a specified position.
     * @param coordinate The position of the game object
     * @param orientation object orientation
     * @param movementRange The movement range of the player
     * @param movementAxis The movement axis of the player
     * @param movementSpeed The speed of the movement of this object
     */
    public AutoMovingObject(Position coordinate, Directions orientation, int movementRange, Axis movementAxis, double movementSpeed) {
        super(coordinate);
        this.orientation = orientation;
        this.movementRange = movementRange;
        this.movementAxis = movementAxis;
        this.movementSpeed = movementSpeed;
        this.lastUpdate = 0;

        if (movementAxis == Axis.X_AXIS)
            defaultDir = Directions.LEFT;
        else
            defaultDir = Directions.DOWN;
    }

    /************************** Functions/Getter/Setter **************************/

    /**
     * Checks if we should move the object or not according to its movement speed
     * @param timeElapsed time elapsed till last update
     * @return true if it should move otherwise false
     */
    public boolean shouldMove(double timeElapsed) {
        this.lastUpdate += timeElapsed;
        if (lastUpdate > movementSpeed) {
            lastUpdate = 0;
            return true;
        }
        return false;
    }

    /**
     * Have to call this in every tick
     * @param level game level
     * @param timeElapsed time elapsed till last update
     */
    public void updatePosition(Level level, double timeElapsed) {

        if (!shouldMove(timeElapsed))
            return;

        if (movementAxis == Axis.X_AXIS) {
            if (defaultDir == Directions.LEFT) {
                int locationDiff = (int)(getInitialPos().getXCoord()-getX());
                if (locationDiff < movementRange)
                    moveLeft(level);
                else
                    defaultDir = Directions.RIGHT;
            } else if (defaultDir == Directions.RIGHT) {
                int locationDiff = (int)(getInitialPos().getXCoord()-getX());
                if (locationDiff > -movementRange)
                    moveRight(level);
                else
                    defaultDir = Directions.LEFT;
            }
        } else if (movementAxis == Axis.Y_AXIS) {
            if (defaultDir == Directions.DOWN) {
                int locationDiff = (int)(getInitialPos().getYCoord()-getY());
                if (locationDiff > -movementRange)
                    moveDown(level);
                else
                    defaultDir = Directions.UP;
            } else if (defaultDir == Directions.UP) {
                int locationDiff = (int)(getInitialPos().getYCoord()-getY());
                if (locationDiff < movementRange)
                    moveUp(level);
                else
                    defaultDir = Directions.DOWN;
            }
        }
    }

    /**
     * Gets the orientation
     * @return orientation
     */
    public Directions getOrientation() {
        return orientation;
    }

    /**
     * Sets the orientation
     * @return orientation
     */
    public void setOrientation(Directions orientation) {
        this.orientation = orientation;
    }



}