
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Base class for rewards
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public abstract class Reward extends StaticObject {

    /**
     * Constructs rewards
     * @param position position of the reward
     */
    public Reward(Position position) {
        super(position);
    }

    /************************ Function/Getter/Setter ************************/

    /**
     * Uses reward and updates it in the model
     * @param model game model
     */
    public abstract void useReward(GameModel model);

}
