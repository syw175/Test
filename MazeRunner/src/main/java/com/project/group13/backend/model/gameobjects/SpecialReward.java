
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;

/**
 * Represents special rewards in the game, it has to be collected
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SpecialReward extends Reward {

    private final int spawn_chance;
    private boolean is_spawned;
    private final int auto_destroy_time;
    private double up_time;

    /**
     * Constructs rewards
     * @param position position of the reward
     * @param spawn_chance chance of spawning this item
     * @param auto_destroy_time time after the item will destroy itself from the game
     */
    public SpecialReward(Position position, int spawn_chance, int auto_destroy_time) {
        super(position);
        this.spawn_chance = spawn_chance;
        this.is_spawned = false;
        this.auto_destroy_time = auto_destroy_time;
        this.up_time = 0;
    }

    /************************ Functions/Getter/Setter ************************/

    /**
     * Override it in the child class, to make the object effect
     * @param model game model
     */
    @Override
    public void useReward(GameModel model) {}

    /**
     * get current elapsed time
     * @return elapsed time
     */
    public int getUpTime() {
        return (int) up_time;
    }

    /**
     * Add elapsed time in seconds
     * @param elapsed elapsed time in seconds
     */
    public void addUpTime(double elapsed) {
        this.up_time += elapsed;
    }

    /**
     * Is reward spawned
     * @return true if yes otherwise false
     */
    public boolean isSpawned() {
        return is_spawned;
    }

    /**
     * Sets item as spawned
     */
    public void setSpawned() {
        this.is_spawned = true;
    }

    /**
     * Sets item as hidden
     */
    public void setHidden() {
        this.up_time = 0;
        this.is_spawned = false;
    }

    /**
     * is times up
     */
    public boolean isTimesUp() {
        return auto_destroy_time <= up_time;
    }

    /**
     * chance of spawning the item
     * @return spawn chance
     */
    public int getSpawnChance() {
        return spawn_chance;
    }

}
