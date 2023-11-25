
package com.project.group13.backend.model.gameobjects;

import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.MovingObject;
import com.project.group13.backend.model.levels.Level;

/**
 * Player object
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Player extends MovingObject {

    private int health;
    private int power;
    private double timeElapsed; // time elapsed till last player update
    private final int healthDepletionRate;
    public static int MAX_HEALTH = 100;
    public static int MAX_POWER = 10;

    /**
     * Constructs the player object based on the position
     * @param pos position
     */
    public Player(Position pos) {
        super(pos);
        this.health = 100; // base 100 health
        this.power = 10; // base 10 power
        this.healthDepletionRate = 2;
        this.timeElapsed = 0;
    }

    /************************** Functions **************************/

    /**
     * Updates last updated time
     * @param elapsed time elapsed
     */
    public void update(double elapsed) {
        this.timeElapsed += elapsed;
        if (timeElapsed > healthDepletionRate) {
            decreasePower(1);
            this.timeElapsed = 0;
        }
    }

    /**
     * Moves the player to specified direction
     * @param directions direction
     * @param level current level
     */
    public void moveTo(Directions directions, Level level) {
        switch (directions) {
            case UP -> moveUp(level);
            case DOWN -> moveDown(level);
            case LEFT -> moveLeft(level);
            case RIGHT -> moveRight(level);
            default -> {}
        }
    }

    /**
     * Resets player location and regains health and power
     */
    public void respawn() {
        this.health = 100;
        this.power = 5;
        this.setPosition(getInitialPos().getXCoord(), getInitialPos().getYCoord());
    }

    /**
     * damages the player
     * @param value damage by value
     */
    public void damage(int value) {
        if ((health-value) > 0)
            this.health -= value;
        else
            this.health = 0;
    }

    /**
     * Heals the player
     * @param value heal by value
     */
    public void heal(int value) {
        if ((value+health) < Player.MAX_HEALTH)
            this.health += value;
        else
            this.health = Player.MAX_HEALTH;
    }

    /**
     * Increase the power
     * @param value power up by value
     */
    public void increasePower(int value) {
        if ((value+power) < Player.MAX_POWER)
            this.power += value;
        else
            this.power = Player.MAX_POWER;
    }

    /**
     * Decrease the power
     * @param value power down by value
     */
    public void decreasePower(int value) {
        if ((power-value) > 0)
            this.power -= value;
        else
            this.power = 0;
    }

    /************************ Getter/Setter ************************/

    /**
     * gets current health value
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * gets current power value
     * @return power
     */
    public int getPower() {
        return power;
    }

    /**
     * checks if player's health is zero or less than that
     * @return true if player is dead
     */
    public boolean isDead() {
        return this.health <= 0;
    }

}