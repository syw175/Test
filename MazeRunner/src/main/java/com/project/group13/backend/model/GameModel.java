
package com.project.group13.backend.model;

import com.project.group13.backend.model.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModel holds all the relevant data for the current state of the game, including the player,
 * enemies, rewards, barriers, and managers for score, lives, levels, and time. It serves as a central
 * data store that the rest of the game can use to query and manipulate game state.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class GameModel {

    private Player player;
    private List<Reward> rewards;
    private List<Barrier> barriers;
    private List<Enemy> enemies;
    private final ScoreManager scoreManager;
    private final LivesManager livesManager;
    private final LevelManager levelManager;
    private final TimeManager timeManager;

    /**
     * Constructs GameModel
     */
    public GameModel() {
        this.rewards = new ArrayList<>();
        this.barriers = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.scoreManager = new ScoreManager();
        this.livesManager = new LivesManager(3);
        this.timeManager = new TimeManager();

        this.levelManager = new LevelManager(this);

        int[] size = this.levelManager.getLevelMap().getMap().getSize();
        this.player = new Player(new Position(size[1]-3, size[0]-3));
    }

    /************************ Getter/Setter ************************/

    /**
     * Get player
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set player
     * @param player player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets list of rewards
     * @return rewards list
     */
    public List<Reward> getRewards() {
        return rewards;
    }

    /**
     * Gets list of barriers
     * @return barriers list
     */
    public List<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * Gets list of enemies
     * @return enemies list
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets remaining rewards count
     * @return remaining rewards count
     */
    public int getRewardsRemaining() {
        int size = 0;
        for (Reward r : rewards) {
            if (r instanceof NormalReward)
                size++;
        }
        return size;
    }

    /**
     * Gets the score manager
     * @return score manager
     */
    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    /**
     * Gets the lives manager
     * @return lives manager
     */
    public LivesManager getLivesManager() {
        return livesManager;
    }

    /**
     * Gets the level manager
     * @return level manager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Gets the level manager
     * @return level manager
     */
    public TimeManager getTimeManager() {
        return timeManager;
    }

}
