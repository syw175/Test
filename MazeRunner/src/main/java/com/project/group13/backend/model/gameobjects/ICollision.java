
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.levels.Level;

/**
 * For collision detection
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface ICollision {

    /**
     * Checks if current player can move to given position
     * @param position Next position
     * @param level Current level
     * @return true if valid otherwise false
     */
    public boolean isCoordinateValid(Position position, Level level);

}
