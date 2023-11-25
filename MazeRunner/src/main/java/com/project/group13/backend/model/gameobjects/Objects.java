
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.Tile;
import com.project.group13.backend.model.levels.Level;
import com.project.group13.frontend.view.BaseView;

/**
 * Represents a base class for game objects.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Objects {

    private Position coordinate;

    /**
     * Constructs a game object with a specified position.
     *
     * @param coordinate The position of the game object.
     */
    public Objects(Position coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Checks if this object collided with the given object
     * @param gameObject object on which we'll check
     * @return true if collided otherwise false
     */
    public boolean collidedWith(Objects gameObject) {
        double x1 = getCoordinate().getXCoord();
        double y1 = getCoordinate().getYCoord();
        double x2 = gameObject.getCoordinate().getXCoord();
        double y2 = gameObject.getCoordinate().getYCoord();
        return x1 == x2 && y1 == y2;
    }

    /************************ Getter/Setter ************************/

    /**
     * Retrieves the position of the game object.
     *
     * @return The position of the game object.
     */
    public Position getCoordinate() {
        return coordinate;
    }

    /**
     * Sets position
     * @param newPos position object
     */
    public void setCoordinate(Position newPos) {
        this.coordinate = newPos;
    }

    /**
     * gets x position
     * @return x pos
     */
    public double getX() {
        return this.coordinate.getXCoord();
    }

    /**
     * gets y position
     * @return y pos
     */
    public double getY() {
        return this.coordinate.getYCoord();
    }

    /**
     * gets position
     * @return x and y position
     */
    public double[] getPosition() {
        return new double[]{getX(), getY()};
    }

    /**
     * Sets x position
     * @param x x location
     */
    public void setX(double x) {
        this.coordinate = new Position(x, getY());
    }

    /**
     * Sets y position
     * @param y y location
     */
    public void setY(double y) {
        this.coordinate = new Position(getX(), y);
    }

    /**
     * Sets position
     * @param x x pos
     * @param y y pos
     */
    public void setPosition(double x, double y) {
        this.coordinate = new Position(x, y);
    }

}