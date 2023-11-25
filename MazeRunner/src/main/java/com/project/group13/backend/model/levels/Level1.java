
package com.project.group13.backend.model.levels;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.*;

/**
 * Level1 implements the Level interface and defines the specific layout and initialization for the first level
 * of the game. It initializes the map layout, places rewards and enemies, and sets their behavior for this level.
 * The layout is represented by a matrix where different integers represent different types of tiles or spaces.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Level1 implements Level {

    private final GameMap gameMap; // The game map specific to Level 1.

    /**
     * Constructor for Level1. It creates a game map based on a predefined array representing the level's layout.
     * A value of 0 may represent an empty space, 1 a walkable tile, and 2 a wall or obstacle.
     */
    public Level1() {
        final int[][] map = new int[][]{
                {0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        this.gameMap = new GameMap(map);
    }

    /************************ Getter/Setter ************************/

    @Override
    public GameMap getMap() {
        return this.gameMap;
    }

    @Override
    public void initModels(GameModel model) {

        // place life reward
        LifeReward lifeReward = new LifeReward(new Position(21, 8), 1);
        LifeReward lifeReward2 = new LifeReward(new Position(8, 14), 1);

        // place health rewards
        HealthReward healthReward = new HealthReward(new Position(12, 6));
        HealthReward healthReward2 = new HealthReward(new Position(2, 12));

        // add rewards
        model.getRewards().add(lifeReward);
        model.getRewards().add(lifeReward2);

        model.getRewards().add(healthReward);
        model.getRewards().add(healthReward2);

        // place static enemies
        SpikeBall spikeBall = new SpikeBall(new Position(18, 12), 8);
        SpikeBall spikeBall2 = new SpikeBall(new Position(11, 12), 6);
        SpikeBall spikeBall3 = new SpikeBall(new Position(17, 4), 9);
        SpikeBall spikeBall4 = new SpikeBall(new Position(5, 4), 2);
        SpikeBall spikeBall5 = new SpikeBall(new Position(2, 16), 5);

        // place moving normal enemies
        Spikes spikes = new Spikes(new Position(17, 3), 1, Axis.X_AXIS, Directions.DOWN, 500);
        Spikes spikes2 = new Spikes(new Position(4, 6), 2, Axis.Y_AXIS, Directions.RIGHT, 50);
        Spikes spikes3 = new Spikes(new Position(16, 13), 2, Axis.X_AXIS, Directions.UP, 200);

        // add enemies
        model.getEnemies().add(spikeBall);
        model.getEnemies().add(spikeBall2);
        model.getEnemies().add(spikeBall3);
        model.getEnemies().add(spikeBall4);
        model.getEnemies().add(spikeBall5);

        model.getBarriers().add(spikes);
        model.getBarriers().add(spikes2);
        model.getBarriers().add(spikes3);

        // place special items
        PowerReward power = new PowerReward(new Position(14, 1));
        PowerReward power1 = new PowerReward(new Position(17, 1));
        PowerReward power2 = new PowerReward(new Position(10, 16));
        PowerReward power3 = new PowerReward(new Position(15, 16));

        model.getRewards().add(power);
        model.getRewards().add(power1);
        model.getRewards().add(power2);
        model.getRewards().add(power3);

    }

}
