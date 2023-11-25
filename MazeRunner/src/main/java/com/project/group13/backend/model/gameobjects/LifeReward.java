
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Extends player life
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LifeReward extends NormalReward {

    private final int increaseLifeBy;

    /**
     * Constructs rewards
     * @param position position of the reward
     */
    public LifeReward(Position position, int increaseBy) {
        super(position);
        this.increaseLifeBy = increaseBy;
    }

    /************************ Functions/Getter/Setter ************************/

    @Override
    public void useReward(GameModel model) {
        int currLives = model.getLivesManager().getLives();
        model.getLivesManager().setLives(currLives+increaseLifeBy);
    }

}
