
package com.project.group13.backend;

import com.project.group13.backend.model.*;
import com.project.group13.backend.model.gameobjects.*;
import com.project.group13.backend.model.levels.Level;

import java.util.List;
import java.util.Random;

/**
 * BackendCtrl is responsible for controlling the backend logic of the game. It manages the game's state transitions,
 * interactions between the player and game objects, such as rewards and enemies, and updates the positions and states
 * of movable objects. It also handles the spawning and time management of special rewards within the game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class BackendCtrl {

    private GameModel gameModel;
    private Random random;

    // Constants for score
    private static final int MAX_RAND = 1000;
    private static final int NORMAL_REWARD_SCORE = 5;
    private static final int SPECIAL_REWARD_SCORE = 10;
    private static final int TRAP_ENEMY_SCORE = 20;
    private static final int NORMAL_ENEMY_SCORE = 30;
    private static final int SPECIAL_ENEMY_SCORE = 40;

    public static GameState currState = GameState.IDLE;

    /**
     * Constructs backend ctrl
     * @param gameModel game model
     */
    public BackendCtrl(GameModel gameModel) {
        this.gameModel = gameModel;
        this.random = new Random();
    }

    /************************** Functions **************************/

    /**
     * Goes to next level
     */
    public void nextLevel() {
        currState = GameState.LOADING;
        this.resetGame();
        gameModel.getLevelManager().nextLevel();
        currState = GameState.IDLE;
    }

    /**
     * Goes to first level
     */
    public void resetLevel() {
        currState = GameState.LOADING;
        this.resetGame();
        gameModel.getLevelManager().resetLevel();
        currState = GameState.IDLE;
    }

    /**
     * Resets the game
     */
    private void resetGame() {
        gameModel.getLivesManager().setLives(3);
        gameModel.getEnemies().clear();
        gameModel.getRewards().clear();
        gameModel.getBarriers().clear();
        gameModel.getPlayer().respawn();
        gameModel.getScoreManager().resetScore();
        gameModel.getTimeManager().reset();
    }

    /**
     * Checks if player has collided with rewards,
     * if yes then it uses and removes them
     */
    public void checkRewardsCollision() {

        Player player = gameModel.getPlayer();
        List<Reward> rewards = gameModel.getRewards();

        // use the reward which collided
        for (Reward r : rewards) {
            // If the reward is a normal reward
            if (r instanceof NormalReward nr) {
                // If the player collided with the reward
                if (player.collidedWith(nr)) {
                    SoundManager.playCollectSound();
                    gameModel.getScoreManager().addScore(NORMAL_REWARD_SCORE);
                    r.useReward(gameModel);
                    gameModel.getRewards().remove(r);
                    break;
                }
            // Otherwise if the reward is a special reward
            } else if (r instanceof SpecialReward sr) {
                if (player.collidedWith(sr)) {
                    if (sr.isSpawned()) {
                        SoundManager.playClickSound();
                        gameModel.getScoreManager().addScore(SPECIAL_REWARD_SCORE);
                        r.useReward(gameModel);
                        ((SpecialReward) r).setHidden();
                        break;
                    }
                }
            }
        }

    }

    /**
     * Checks if player has collided with enemies,
     * if yes then it damages the player and gets removed
     */
    public void checkEnemiesCollision() {

        Player player = gameModel.getPlayer();
        List<Enemy> enemies = gameModel.getEnemies();

        // attack the player if it is in range or collided with the player
        for (Enemy e : enemies) {
            if (e instanceof TrapEnemy tr) {
                if (player.collidedWith(tr)) {
                    SoundManager.playDamageSound();
                    if (tr.getPower() < player.getPower()) {
                        gameModel.getScoreManager().addScore(TRAP_ENEMY_SCORE);
                        gameModel.getEnemies().remove(e);
                    } else {
                        e.attack(player);
                    }
                    break;
                }
            } else if (e instanceof NormalEnemy ne) {
                // If the enemy collided with an instance of normal enemy
                if (player.collidedWith(ne)) {
                    SoundManager.playDamageSound();
                    if (ne.getPower() < player.getPower()) {
                        gameModel.getScoreManager().addScore(NORMAL_ENEMY_SCORE);
                        gameModel.getEnemies().remove(e);
                    } else {
                        e.attack(player);
                    }
                    break;
                }
            } else if (e instanceof SpecialEnemy se) {
                // If the player collided with an instance of special enemy
                if (player.collidedWith(se)) {
                    SoundManager.playDamageSound();
                    if (se.getPower() < player.getPower()) {
                        gameModel.getScoreManager().addScore(SPECIAL_ENEMY_SCORE);
                        gameModel.getEnemies().remove(e);
                    } else {
                        e.attack(player);
                    }
                    break;
                }
            }
        }

    }

    /**
     * Checks if player has collided with barriers,
     * if yes then it damages the player and gets removed
     */
    public void checkBarriersCollision() {

        Player player = gameModel.getPlayer();
        List<Barrier> barriers = gameModel.getBarriers();

        // damages the player if it is in range or collided with the player
        for (Barrier b : barriers) {
            if (b instanceof NormalBarrier nb) {
                if (player.collidedWith(nb)) {
                    SoundManager.playDamageSound();
                    b.damage(player);
                    break;
                }
            } else if (b instanceof SpecialBarrier sb) {
                if (player.collidedWith(sb)) {
                    SoundManager.playDamageSound();
                    b.damage(player);
                    break;
                }
            }
        }

    }

    /**
     * Updates player positions which are movable
     * @param deltaTime elapsed time till last update
     */
    public void updateObjectsPosition(double deltaTime) {

        // movable enemies
        List<Enemy> enemies = gameModel.getEnemies();

        // movable barriers
        List<Barrier> barriers = gameModel.getBarriers();

        Level level = gameModel.getLevelManager().getLevelMap();

        // update position of enemies and barriers for each type
        for (Enemy e : enemies) {
            if (e instanceof AutoMovingObject) {
                ((AutoMovingObject) e).updatePosition(level, deltaTime);
            }
        }

        for (Barrier b : barriers) {
            if (b instanceof AutoMovingObject) {
                ((AutoMovingObject) b).updatePosition(level, deltaTime);
            }
        }

    }

    /**
     * Spawns special rewards based on their chances
     */
    public void spawnSpecialRewards() {

        List<Reward> rewards = gameModel.getRewards();

        // check if any special reward is already spawned
        for (Reward r : rewards) {
            if (r instanceof SpecialReward sr) {
                if (sr.isSpawned())
                    return;
            }
        }

        // spawn a special reward if no special reward is spawned
        for (Reward r : rewards) {
            if (r instanceof SpecialReward sr) {
                int rand = random.nextInt(MAX_RAND);
                if (rand == sr.getSpawnChance()) {
                    sr.setSpawned();
                    break;
                }
            }
        }

    }

    /**
     * updates up time in special rewards
     * @param elapsed elapsed time in seconds
     */
    public void updateUpTimeForSpecialRewards(double elapsed) {

        List<Reward> rewards = gameModel.getRewards();

        // check if any special reward is already spawned, then updates its up-time if it is
        for (Reward r : rewards) {
            if (r instanceof SpecialReward sr) {
                if (sr.isSpawned()) {
                    sr.addUpTime(elapsed);
                    return;
                }
            }
        }

    }

    /**
     * Removes special rewards after time is up
     */
    public void checkTimeUpForSpecialReward() {

        List<Reward> rewards = gameModel.getRewards();

        // check if time is up for any special reward which is spawned
        for (Reward r : rewards) {
            if (r instanceof SpecialReward sr) {
                if (sr.isSpawned()) {
                    if (sr.isTimesUp()) {
                        sr.setHidden();
                    }
                }
            }
        }

    }

}
