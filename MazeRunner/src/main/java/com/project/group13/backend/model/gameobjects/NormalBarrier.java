package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Position;

/**
 * Normal barrier in the game, non-moving
 */
public class NormalBarrier extends StaticObject implements Barrier {

    /**
     * Constructs a non-moving game object with a specified position.
     * @param coordinate The position of the game object.
     */
    public NormalBarrier(Position coordinate) {
        super(coordinate);
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Damages the player and removes 100HP health
     * Update it as you want in the subclass, by extending this
     * @param player player
     */
    @Override
    public void damage(Player player) {
        player.damage(100);
    }

}