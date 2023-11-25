
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.levels.BlockType;
import com.project.group13.backend.model.levels.GameMap;
import com.project.group13.backend.model.levels.Level;

/**
 * Moving game object
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class MovingObject extends Objects implements ICollision {

    private final Position initialPos;

    private Directions currMoveDir;

    /**
     * Constructs a moving game object with a specified position.
     *
     * @param coordinate The position of the game object.
     */
    public MovingObject(Position coordinate) {
        super(coordinate);
        this.initialPos = coordinate;
        this.currMoveDir = Directions.UP;
    }

    /************************** Functions/Getters/Setters **************************/

    /**
     * Moves the object to up direction
     */
    public void moveUp(Level currLevel) {
        Position newPos = new Position(getCoordinate().getXCoord(), getCoordinate().getYCoord()- 1);
        if (isCoordinateValid(newPos, currLevel)) {
            setCoordinate(newPos);
            this.currMoveDir = Directions.UP;
        }
    }

    /**
     * Moves the object to down direction
     */
    public void moveDown(Level currLevel) {
        Position newPos = new Position(getCoordinate().getXCoord(), getCoordinate().getYCoord()+1);
        if (isCoordinateValid(newPos, currLevel)) {
            setCoordinate(newPos);
            this.currMoveDir = Directions.DOWN;
        }
    }

    /**
     * Moves the object to left direction
     */
    public void moveLeft(Level currLevel) {
        Position newPos = new Position(getCoordinate().getXCoord()-1, getCoordinate().getYCoord());
        if (isCoordinateValid(newPos, currLevel)) {
            setCoordinate(newPos);
            this.currMoveDir = Directions.LEFT;
        }
    }

    /**
     * Moves the object to right direction
     */
    public void moveRight(Level currLevel) {
        Position newPos = new Position(getCoordinate().getXCoord()+1, getCoordinate().getYCoord());
        if (isCoordinateValid(newPos, currLevel)) {
            setCoordinate(newPos);
            this.currMoveDir = Directions.RIGHT;
        }
    }

    /**
     * Checks if the current coordinate of the object is valid.
     *
     * @param newPos New position where you want the object to be
     * @param level level in which the object is
     * @return True if the coordinate is valid, false otherwise.
     */
    @Override
    public boolean isCoordinateValid(Position newPos, Level level) {
        GameMap map = level.getMap();
        BlockType type = map.getBlockAt((int) newPos.getYCoord(), (int) newPos.getXCoord());
        return type == BlockType.EMPTY || type == BlockType.NEXT_LEVEL;
    }

    /**
     * Gets the initial position of the object
     * @return initial position
     */
    public Position getInitialPos() {
        return initialPos;
    }

    /**
     * Gets current movement direction
     * @return direction
     */
    public Directions getCurrMoveDir() {
        return currMoveDir;
    }

}