
package com.project.group13.backend.model.levels;

import com.project.group13.backend.model.GameModel;

/**
 * The Level interface defines the structure for level classes, which represent different stages or maps in the game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface Level {

    /**
     * Gets the game map associated with the level. The game map includes the layout and static elements
     * that make up the level's structure, such as walls, paths, and barriers.
     *
     * @return The GameMap object containing the level's structure and layout.
     */
    public GameMap getMap();

    /**
     * Initialize game models according to this level
     * @param model model
     */
    public void initModels(GameModel model);

}
