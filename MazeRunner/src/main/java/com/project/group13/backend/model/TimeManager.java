
package com.project.group13.backend.model;

/**
 * Manages game timer
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class TimeManager {

    private double elapsedTimeInSeconds;

    /**
     * Constructs time manager
     */
    public TimeManager() {
        this.elapsedTimeInSeconds = 0;
    }

    /************************** Functions **************************/

    /**
     * Gets current elapsed time
     * @return time
     */
    public int getElapsedTimeInSeconds() {
        return (int)elapsedTimeInSeconds;
    }

    /**
     * Adds given seconds into the timer
     * @param seconds seconds elapsed since last update
     */
    public void updateTimeBy(double seconds) {
        this.elapsedTimeInSeconds += seconds;
    }

    public void reset() {
        this.elapsedTimeInSeconds = 0;
    }

}
