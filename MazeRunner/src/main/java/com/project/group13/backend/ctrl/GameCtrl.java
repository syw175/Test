
package com.project.group13.backend.ctrl;

import com.project.group13.backend.BackendCtrl;
import com.project.group13.backend.LeaderboardManager;
import com.project.group13.backend.SoundManager;
import com.project.group13.backend.handler.*;
import com.project.group13.backend.model.*;
import com.project.group13.backend.model.gameobjects.Player;
import com.project.group13.backend.model.levels.BlockType;
import com.project.group13.backend.model.levels.Level;
import com.project.group13.frontend.view.GameView;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * GameCtrl extends the InputHandler to control the game's interactive functions. It manages the game's
 * state, processing player inputs, updating the model, and rendering the view. It handles game logic such
 * as collision detection, game progression, and pausing the game.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class GameCtrl extends InputHandler {

    private final GameView gameView; // The view component for the game
    private final GameModel gameModel; // The model component for the game data and logic
    private final BackendCtrl backendCtrl; // The controller component for backend logic
    private boolean isRunning = true; // Flag to control the game loop execution
    private boolean paused = false; // Flag to determine if the game is paused

    /**
     * Constructs a new game controller without a specific JFrame, setting up the game model,
     * view, and backend controller, and starting the game loop.
     */
    public GameCtrl() {
        this.gameModel = new GameModel();
        this.gameView = new GameView(this, gameModel);
        this.backendCtrl = new BackendCtrl(gameModel);
        startGameLoop();
    }

    /**
     * Constructs the game controller with a given JFrame, allowing the game to be embedded into an
     * existing application window.
     *
     * @param jFrame The window frame in which the game should run.
     */
    public GameCtrl(JFrame jFrame) {
        this.gameModel = new GameModel();
        this.gameView = new GameView(jFrame, this, gameModel);
        this.backendCtrl = new BackendCtrl(gameModel);
        startGameLoop();
    }

    /************************** Handlers **************************/

    /**
     * Handles key release events to control player movement, game pausing, level resetting, and
     * advancing to the next level.
     *
     * @param e The key event that triggered the handler.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        Level level = gameModel.getLevelManager().getLevelMap();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                gameModel.getPlayer().moveTo(Directions.UP, level);
            }
            case KeyEvent.VK_DOWN -> {
                gameModel.getPlayer().moveTo(Directions.DOWN, level);
            }
            case KeyEvent.VK_LEFT -> {
                gameModel.getPlayer().moveTo(Directions.LEFT, level);
            }
            case KeyEvent.VK_RIGHT -> {
                gameModel.getPlayer().moveTo(Directions.RIGHT, level);
            }
            case KeyEvent.VK_ESCAPE -> {
                backendCtrl.resetLevel();
                gameView.hideJFrame();
                CtrlFactory.createMenuCtrlInstance();
            }
            case KeyEvent.VK_SPACE -> paused = !paused; // toggle pause
        }
    }

    /************************** Functions **************************/

    /**
     * Starts the main game loop in a new thread. This loop controls the timing of game updates
     * and rendering, as well as pausing and stopping the game.
     */
    private void startGameLoop() {

        Thread gameThread = new Thread(() -> {

            long lastTime = System.currentTimeMillis();
            long targetFrameTime = 1000 / 30;

            // starts the loop
            while (isRunning) {

                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - lastTime;
                lastTime = currentTime;

                if (!paused && BackendCtrl.currState == GameState.IDLE) {
                    // update the game
                    updateGame(deltaTime);

                    // render the game
                    gameView.repaint();
                }

                // Calculates the time taken for the frame
                long frameTime = System.currentTimeMillis() - currentTime;

                // Calculates the remaining time to meet the target frame rate
                long remainingTime = targetFrameTime - frameTime;

                // If the remaining time is positive, sleep for that duration
                if (remainingTime > 0) {
                    try {
                        Thread.sleep(remainingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        gameThread.start();

    }

    /**
     * Updates game state
     * @param deltaTime Elapsed time since last tick
     */
    private void updateGame(double deltaTime) {

        double timeInSecs = (deltaTime/1000);

        // add time in the timer
        gameModel.getTimeManager().updateTimeBy(timeInSecs);

        // handle rewards collisions
        backendCtrl.checkRewardsCollision();

        // handle special rewards spawning
        backendCtrl.spawnSpecialRewards();

        // update time in spawned special rewards
        backendCtrl.updateUpTimeForSpecialRewards(timeInSecs);

        // check if any special reward has been timed up
        backendCtrl.checkTimeUpForSpecialReward();

        // handle barriers
        backendCtrl.checkBarriersCollision();

        // handle enemies
        backendCtrl.checkEnemiesCollision();

        // checks if player is dead after fighting with enemies
        if (gameModel.getPlayer().isDead()) {
            gameModel.getLivesManager().removeLife();
            gameModel.getPlayer().respawn();
        } else {
            gameModel.getPlayer().update(timeInSecs);
        }

        // handle movements
        backendCtrl.updateObjectsPosition(deltaTime);

        // check if player has reached the final position
        Player player = gameModel.getPlayer();
        BlockType type = gameModel.getLevelManager().getLevelMap().getMap().getBlockAt((int) player.getY(), (int) player.getX());

        if (type == BlockType.NEXT_LEVEL) {
            if (gameModel.getRewardsRemaining() > 0) {
                player.moveDown(gameModel.getLevelManager().getLevelMap());
            } else {
                if (SoundManager.MUSIC_ON) {
                    SoundManager.toggleMusic();
                    SoundManager.playWinSound();
                    SoundManager.toggleMusic();
                } else {
                    SoundManager.playWinSound();
                }

                // update score for survival time
                int time = gameModel.getTimeManager().getElapsedTimeInSeconds();
                gameModel.getScoreManager().addScore((10000.0/(double)time));

                // update current score in total score
                gameModel.getScoreManager().addScoreInTotal(gameModel.getScoreManager().getScore());

                // updates the game to next level
                if (gameModel.getLevelManager().nextLevelAvailaible()) {
                    // level-win
                    JOptionPane.showMessageDialog(gameView, "You won this level\nYour Score is: " + gameModel.getScoreManager().getScore() + "\nNow try the next level");
                    backendCtrl.nextLevel();
                } else {
                    // game-win
                    JOptionPane.showMessageDialog(gameView, "You won the game\nYour Score is: " + gameModel.getScoreManager().getScore() + "\nYour total score is: " + gameModel.getScoreManager().getTotalScore() + "\nGame will exit to main menu");

                    // add score to the leaderboard
                    LeaderboardManager.addScore(gameModel.getScoreManager().getTotalScore());

                    // resets level data
                    backendCtrl.resetLevel();

                    // game-over
                    isRunning = false;

                    // hide frame and return to menu
                    gameView.hideJFrame();
                    CtrlFactory.createMenuCtrlInstance(gameView.getFrame());
                }
            }
        }

        // check if player is alive
        if (gameModel.getLivesManager().getLives() <= 0) {
            if (SoundManager.MUSIC_ON) {
                SoundManager.toggleMusic();
                SoundManager.playDieSound();
                SoundManager.toggleMusic();
            } else {
                SoundManager.playWinSound();
            }

            // add score to leaderboard
            gameModel.getScoreManager().addScoreInTotal(gameModel.getScoreManager().getScore());
            LeaderboardManager.addScore(gameModel.getScoreManager().getTotalScore());

            // game-over
            isRunning = false;
            JOptionPane.showMessageDialog(gameView, "Game Over\nYour Score is: " + gameModel.getScoreManager().getScore());

            // hide frame
            gameView.hideJFrame();
            CtrlFactory.createMenuCtrlInstance(gameView.getFrame());
        }

    }

}