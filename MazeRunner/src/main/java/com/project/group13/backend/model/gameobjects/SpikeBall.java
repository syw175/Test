
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Position;

/**
 * SpikeBall enemy (non-moving)
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SpikeBall extends TrapEnemy {

    /**
     * Constructs a static game object with a specified position.
     * @param coordinate The position of the game object.
     * @param power PowerReward of the spike ball (max 10)
     */
    public SpikeBall(Position coordinate, int power) {
        super(coordinate, power);
    }

    /************************ Functions/Getter/Setter ************************/

}
