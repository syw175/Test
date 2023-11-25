
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Represents a special power reward in the game
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class PowerReward extends SpecialReward {

    /**
     * Constructs rewards
     * @param position position of the reward
     */
    public PowerReward(Position position) {
        super(position, 1, 10);
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Increases power of the player to full
     * @param model game model
     */
    @Override
    public void useReward(GameModel model) {
        model.getPlayer().increasePower(Player.MAX_POWER);
    }

}
