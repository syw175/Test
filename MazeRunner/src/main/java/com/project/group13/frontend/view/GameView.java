
package com.project.group13.frontend.view;

import com.project.group13.backend.ResLoader;
import com.project.group13.backend.handler.InputHandler;
import com.project.group13.backend.model.*;
import com.project.group13.backend.model.gameobjects.*;
import com.project.group13.backend.model.levels.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * GameView extends the BaseView to provide a specific rendering for the game.
 * It handles the visual representation of all game-related components such as the player,
 * enemies, rewards, and the game level itself. It uses the GameModel for game state information
 * to render the view accordingly. GameView also manages resource initialization for game sprites
 * and drawing functionalities.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class GameView extends BaseView {

    private final GameModel gameModel;

    /**
     * Resources
     */
    private Image playerUp, playerDown, playerLeft, playerRight; // player
    private Image heart, health, power; // rewards
    private Image wallDesignA, wallDesignB, wallDesignC;
    private Image spikeBall, spikesLeft, spikesRight, spikesUp, spikesDown;

    /**
     * Constructs game view
     * @param inputHandler InputHandler object
     */
    public GameView(InputHandler inputHandler, GameModel gameModel) {
        super(inputHandler);
        this.gameModel = gameModel;
        init();
        showView();
    }

    /**
     * Constructs game view
     * @param jFrame JFrame object
     * @param inputHandler InputHandler object
     */
    public GameView(JFrame jFrame, InputHandler inputHandler, GameModel gameModel) {
        super(jFrame, inputHandler);
        this.gameModel = gameModel;
        init();
        revalidateView();
    }

    /*********************** Drawing Methods ***********************/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw game components
        drawBackground(g);
        drawLevel(g);
        drawBarriers(g);
        drawEnemies(g);
        drawRewards(g);
        drawPlayer(g);
        drawLives(g);
        drawTime(g);
        drawScore(g);
        drawRewardsRemaining(g);
    }

    /**
     * Draws the background of the game
     * @param g Graphics object
     */
    private void drawBackground(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BaseView.GAME_WIDTH, BaseView.GAME_HEIGHT);
    }

    /**
     * Draws the map according to the level of the game
     * @param g Graphics object
     */
    private void drawLevel(Graphics g) {
        int level = gameModel.getLevelManager().getLevel();
        switch (level) {
            case 1 -> {
                GameMap gameMap = gameModel.getLevelManager().getLevelMap().getMap();
                final int[] size = gameMap.getSize();
                for (int i=0; i < size[0]; i++) {
                    for (int j=0; j < size[1]; j++) {
                        BlockType type = gameMap.getBlockAt(i, j);
                        if (type == BlockType.WALL) {
                            g.drawImage(wallDesignA, (int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                        } else if (type == BlockType.NEXT_LEVEL) {
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect((int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE);
                        }
                    }
                }
            }
            case 2 -> {
                GameMap gameMap = gameModel.getLevelManager().getLevelMap().getMap();
                final int[] size = gameMap.getSize();
                for (int i=0; i < size[0]; i++) {
                    for (int j=0; j < size[1]; j++) {
                        BlockType type = gameMap.getBlockAt(i, j);
                        if (type == BlockType.WALL) {
                            g.drawImage(wallDesignB, (int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                        } else if (type == BlockType.NEXT_LEVEL) {
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect((int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE);
                        }
                    }
                }
            }
            case 3 -> {
                GameMap gameMap = gameModel.getLevelManager().getLevelMap().getMap();
                final int[] size = gameMap.getSize();
                for (int i=0; i < size[0]; i++) {
                    for (int j=0; j < size[1]; j++) {
                        BlockType type = gameMap.getBlockAt(i, j);
                        if (type == BlockType.WALL) {
                            g.drawImage(wallDesignC, (int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                        } else if (type == BlockType.NEXT_LEVEL) {
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect((int) (j*Tile.SIZE), (int) (i*Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE);
                        }
                    }
                }
            }
            default -> {
                break;
            }
        }
    }

    /**
     * Draws the barriers in the game
     * @param g Graphics object
     */
    private void drawBarriers(Graphics g) {
        List<Barrier> barriers = gameModel.getBarriers();
        for (Barrier b : barriers) {
            if (b instanceof NormalBarrier nb) {
                int i = (int) nb.getY();
                int j = (int) nb.getX();
            } else if (b instanceof SpecialBarrier sb) {
                int i = (int) sb.getY();
                int j = (int) sb.getX();
                if (sb instanceof Spikes s) {
                    Directions orientation = s.getOrientation();
                    if (orientation == Directions.DOWN)
                        g.drawImage(spikesDown, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                    else if (orientation == Directions.UP)
                        g.drawImage(spikesUp, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                    else if (orientation == Directions.LEFT)
                        g.drawImage(spikesLeft, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                    else if (orientation == Directions.RIGHT)
                        g.drawImage(spikesRight, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                }
            }
        }
    }

    /**
     * Draws the player
     * @param g Graphics object
     */
    private void drawPlayer(Graphics g) {
        Player p = gameModel.getPlayer();
        if (p != null) {
            Position pos = new Position(p.getX() * Tile.SIZE, p.getY() * Tile.SIZE);

            // Draw health bar
            int healthBarWidth = (int) ((Tile.SIZE * (((double)p.getHealth() / (double)Player.MAX_HEALTH))));
            int healthBarHeight = 5;

            // Draw health bar border
            g.setColor(Color.BLACK);
            g.drawRect((int) pos.getXCoord(), (int) pos.getYCoord() - healthBarHeight, (int) Tile.SIZE, healthBarHeight);

            // Draw health bar
            g.setColor(Color.RED);
            g.fillRect((int) pos.getXCoord(), (int) pos.getYCoord() - healthBarHeight, healthBarWidth, healthBarHeight);

            // Draw power bar
            int powerBarWidth = (int) ((Tile.SIZE * (((double)p.getPower() / (double)Player.MAX_POWER))));
            int powerBarHeight = 5;

            // Draw power bar border
            g.setColor(Color.BLACK);
            g.drawRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, (int) Tile.SIZE, powerBarHeight);

            // Draw power bar
            g.setColor(Color.BLUE);
            g.fillRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, powerBarWidth, powerBarHeight);

            // Draw player
            if (p.getCurrMoveDir() == Directions.UP)
                g.drawImage(playerUp, (int) pos.getXCoord(), (int) pos.getYCoord(), (int) Tile.SIZE, (int) Tile.SIZE, null);
            else if (p.getCurrMoveDir() == Directions.DOWN)
                g.drawImage(playerDown, (int) pos.getXCoord(), (int) pos.getYCoord(), (int) Tile.SIZE, (int) Tile.SIZE, null);
            else if (p.getCurrMoveDir() == Directions.LEFT)
                g.drawImage(playerLeft, (int) pos.getXCoord(), (int) pos.getYCoord(), (int) Tile.SIZE, (int) Tile.SIZE, null);
            else if (p.getCurrMoveDir() == Directions.RIGHT)
                g.drawImage(playerRight, (int) pos.getXCoord(), (int) pos.getYCoord(), (int) Tile.SIZE, (int) Tile.SIZE, null);
        }
    }

    /**
     * Draws remaining lives
     * @param g Graphics object
     */
    private void drawLives(Graphics g) {
        LivesManager lm = gameModel.getLivesManager();
        if (lm != null) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            g.drawString("Lives: ", (int) Tile.SIZE, (int) (Tile.SIZE+(Tile.SIZE/2)));
            for (int i = 0; i < lm.getLives(); i++)
                g.drawImage(heart, (int) ((Tile.SIZE*1.8) + Tile.SIZE*(i+1)), (int) (Tile.SIZE/1.2), (int) Tile.SIZE, (int) Tile.SIZE, null);
        }
    }

    /**
     * Draws enemies in the game
     * @param g Graphics object
     */
    private void drawEnemies(Graphics g) {
        List<Enemy> enemies = gameModel.getEnemies();
        for (Enemy e : enemies) {
            if (e instanceof TrapEnemy tr) {
                int i = (int) tr.getY();
                int j = (int) tr.getX();

                Position pos = new Position(j * Tile.SIZE, i * Tile.SIZE);

                // calculate power bar size
                int powerBarWidth = (int) ((Tile.SIZE * (((double)tr.getPower() / (double)Enemy.MAX_POWER))));
                int powerBarHeight = 5;

                // Draw power bar border
                g.setColor(Color.BLACK);
                g.drawRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, (int) Tile.SIZE, powerBarHeight);

                // Draw power bar
                g.setColor(Color.BLUE);
                g.fillRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, powerBarWidth, powerBarHeight);

                if (e instanceof SpikeBall) {
                    g.drawImage(spikeBall, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                }
            } else if (e instanceof NormalEnemy ne) {
                int i = (int) ne.getY();
                int j = (int) ne.getX();

                Position pos = new Position(j * Tile.SIZE, i * Tile.SIZE);

                // Draw power bar
                int powerBarWidth = (int) ((Tile.SIZE * (((double)ne.getPower() / (double)Enemy.MAX_POWER))));
                int powerBarHeight = 5;

                // Draw power bar border
                g.setColor(Color.BLACK);
                g.drawRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, (int) Tile.SIZE, powerBarHeight);

                // Draw power bar
                g.setColor(Color.BLUE);
                g.fillRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, powerBarWidth, powerBarHeight);
            } else if (e instanceof SpecialEnemy se) {
                int i = (int) se.getY();
                int j = (int) se.getX();

                Position pos = new Position(j * Tile.SIZE, i * Tile.SIZE);

                // Draw power bar
                int powerBarWidth = (int) ((Tile.SIZE * (((double)se.getPower() / (double)Enemy.MAX_POWER))));
                int powerBarHeight = 5;

                // Draw power bar border
                g.setColor(Color.BLACK);
                g.drawRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, (int) Tile.SIZE, powerBarHeight);

                // Draw power bar
                g.setColor(Color.BLUE);
                g.fillRect((int) pos.getXCoord(), (int) pos.getYCoord() - 10 - powerBarHeight, powerBarWidth, powerBarHeight);
            }
        }
    }

    /**
     * Draw rewards
     * @param g graphics object
     */
    private void drawRewards(Graphics g) {
        List<Reward> rewards = gameModel.getRewards();
        for (Reward r : rewards) {
            int i = (int) r.getY();
            int j = (int) r.getX();
            if (r instanceof NormalReward) {
                if (r instanceof LifeReward)
                    g.drawImage(heart, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                else if (r instanceof HealthReward)
                    g.drawImage(health, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
            } else if (r instanceof SpecialReward sr) {
                if (sr.isSpawned()) {
                    if (r instanceof PowerReward)
                        g.drawImage(power, (int) (j * Tile.SIZE), (int) (i * Tile.SIZE), (int) Tile.SIZE, (int) Tile.SIZE, null);
                }
            }
        }
    }

    /**
     * Draws game timer
     * @param g graphics object
     */
    private void drawTime(Graphics g) {
        int timeInSeconds = gameModel.getTimeManager().getElapsedTimeInSeconds();
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 24));

        if (minutes > 0) {
            g.drawString("Time: " + minutes + "min " + seconds + "sec", 300, 50);
        } else {
            g.drawString("Time: " + seconds + "sec", 300, 50);
        }
    }

    /**
     * Draws game score
     * @param g graphics object
     */
    private void drawScore(Graphics g) {
        int score = gameModel.getScoreManager().getScore();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        g.drawString("Score: " + score, 600, 50);
    }

    /**
     * Draws rewards remaining
     * @param g graphics object
     */
    private void drawRewardsRemaining(Graphics g) {
        int rewardsRemaining = gameModel.getRewardsRemaining();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 24));
        g.drawString("Rewards: " + rewardsRemaining, 600, 74);
    }

    /********************** Internal Methods ***********************/

    /**
     * Initializes components of the game
     */
    private void init() {

        // initialize image resources
        this.playerUp = ResLoader.loadSprite("player_up.png");
        this.playerDown = ResLoader.loadSprite("player_down.png");
        this.playerLeft = ResLoader.loadSprite("player_left.png");
        this.playerRight = ResLoader.loadSprite("player_right.png");
        this.heart = ResLoader.loadSprite("heart.png");
        this.power = ResLoader.loadSprite("power.png");
        this.health = ResLoader.loadSprite("health.png");
        this.wallDesignA = ResLoader.loadSprite("wall1.png");
        this.wallDesignB = ResLoader.loadSprite("wall2.png");
        this.wallDesignC = ResLoader.loadSprite("wall3.png");
        this.spikeBall = ResLoader.loadSprite("spikeBall.png");
        this.spikesDown = ResLoader.loadSprite("spikes_down.png");
        this.spikesUp = ResLoader.loadSprite("spikes_up.png");
        this.spikesLeft = ResLoader.loadSprite("spikes_left.png");
        this.spikesRight = ResLoader.loadSprite("spikes_right.png");

    }

    /**
     * Shows the view,
     * after initializing the jFrame
     */
    private void showView() {
        initJFrame();
        showJFrame();
    }

    /**
     * Shows the view,
     * after revalidating the jFrame
     */
    private void revalidateView() {
        revalidateJFrame();
        showJFrame();
    }

}
