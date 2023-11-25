
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Represents a normal Trap enemy in the game.
 * Traps are non-moving enemies in the game.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class TrapEnemy extends StaticObject implements Enemy {

    private final int power;

    /**
     * Constructs a non-moving game object with a specified position.
     * @param coordinate The position of the game object.
     * @param power PowerReward of the spike ball (max 10)
     */
    public TrapEnemy(Position coordinate, int power) {
        super(coordinate);

        if (power < 0 || power > MAX_POWER) {
            this.power = MAX_POWER;
        } else {
            this.power = power;
        }
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Attacks the player with its power value
     * Update it as you want in the subclass, by extending this
     * @param player player
     */
    @Override
    public void attack(Player player) {
        player.damage(power*10);
    }

    /**
     * gets current power value
     * @return power
     */
    @Override
    public int getPower() {
        return power;
    }

}
