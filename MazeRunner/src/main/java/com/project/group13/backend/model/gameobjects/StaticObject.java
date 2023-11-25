
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.Objects;

/**
 * Non-Moving game object
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class StaticObject extends Objects {

    /**
     * Constructs a non-moving game object with a specified position.
     *
     * @param coordinate The position of the game object.
     */
    public StaticObject(Position coordinate) {
        super(coordinate);
    }

    /************************** Functions **************************/

    @Override
    public void setX(double x) {}

    @Override
    public void setY(double y) {}

    @Override
    public void setPosition(double x, double y) {}

    @Override
    public void setCoordinate(Position newPos) {}

}