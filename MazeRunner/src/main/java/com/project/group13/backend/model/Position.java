
package com.project.group13.backend.model;

/**
 * Represents a position in the game grid with X and Y coordinates.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Position {
    private double xCoord;
    private double yCoord;

    /**
     * Constructs a Position object with the specified X and Y coordinates.
     *
     * @param xCoord The X coordinate.
     * @param yCoord The Y coordinate.
     */
    public Position(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Retrieves the X coordinate of the position.
     *
     * @return The X coordinate.
     */
    public double getXCoord() {
        return xCoord;
    }

    /**
     * Retrieves the Y coordinate of the position.
     *
     * @return The Y coordinate.
     */
    public double getYCoord() {
        return yCoord;
    }

    /**
     * Retrieves the position as a list of double values [X, Y].
     *
     * @return A list containing the X and Y coordinates.
     */
    public double[] getPosition() {
        return new double[]{xCoord, yCoord};
    }

}
