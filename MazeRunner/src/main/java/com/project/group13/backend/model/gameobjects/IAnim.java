
package com.project.group13.backend.model.gameobjects;

/**
 * For animations in the game
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface IAnim {

    public final int LOW_FPS = 30;
    public final int MAX_FPS = 60;

    /**
     * Updates animations according to the object state
     */
    public void updateAnim();

    /**
     * Updates frames based on time elapsed
     * @param elapsed time elapsed
     */
    public void updateFrame(double elapsed);

    /**
     * Changes state of the object
     * @param state object state
     */
    public void changeState(ObjState state);

}
