
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Heals player
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class HealthReward extends NormalReward {

    /**
     * Constructs rewards
     * @param position position of the reward
     */
    public HealthReward(Position position) {
        super(position);
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Heals the player with half of its max health value
     * @param model game model
     */
    @Override
    public void useReward(GameModel model) {
        model.getPlayer().heal(Player.MAX_HEALTH/2);
    }

}
