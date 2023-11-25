
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Represents normal rewards in the game, it has to be collected
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class NormalReward extends Reward {

    /**
     * Constructs rewards
     * @param position position of the reward
     */
    public NormalReward(Position position) {
        super(position);
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Override it in the child class, to make the object effect
     * @param model game model
     */
    @Override
    public void useReward(GameModel model) {}

}
