
package com.project.group13.backend.model.levels;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.*;

/**
 * Level2 implements the Level interface and defines the specific layout and initialization for the first level
 * of the game. It initializes the map layout, places rewards and enemies, and sets their behavior for this level.
 * The layout is represented by a matrix where different integers represent different types of tiles or spaces.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Level2 implements Level {

    private final GameMap gameMap; // The game map specific to Level 2.

    /**
     * Constructs game map for level 2 (Similar to Level 1)
     */
    public Level2() {
        final int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
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
        LifeReward lifeReward = new LifeReward(new Position(3, 3), 1);
        LifeReward lifeReward2 = new LifeReward(new Position(22, 7), 1);

        // place health rewards
        HealthReward healthReward = new HealthReward(new Position(8, 14));
        HealthReward healthReward2 = new HealthReward(new Position(19, 6));
        HealthReward healthReward3 = new HealthReward(new Position(3, 15));

        // add rewards
        model.getRewards().add(lifeReward);
        model.getRewards().add(lifeReward2);

        model.getRewards().add(healthReward);
        model.getRewards().add(healthReward2);
        model.getRewards().add(healthReward3);

        // place static enemies
        SpikeBall spikeBall = new SpikeBall(new Position(18, 12), 8);
        SpikeBall spikeBall2 = new SpikeBall(new Position(8, 10), 6);
        SpikeBall spikeBall3 = new SpikeBall(new Position(16, 4), 9);
        SpikeBall spikeBall4 = new SpikeBall(new Position(5, 4), 2);
        SpikeBall spikeBall5 = new SpikeBall(new Position(2, 14), 5);

        // place moving normal enemies
        Spikes spikes = new Spikes(new Position(20, 1), 3, Axis.X_AXIS, Directions.DOWN, 150);
        Spikes spikes2 = new Spikes(new Position(7, 9), 5, Axis.X_AXIS, Directions.DOWN, 50);
        Spikes spikes3 = new Spikes(new Position(12, 13), 1, Axis.X_AXIS, Directions.UP, 250);

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
        PowerReward power = new PowerReward(new Position(2, 7));
        PowerReward power1 = new PowerReward(new Position(15, 1));
        PowerReward power2 = new PowerReward(new Position(10, 15));
        PowerReward power3 = new PowerReward(new Position(1, 16));

        model.getRewards().add(power);
        model.getRewards().add(power1);
        model.getRewards().add(power2);
        model.getRewards().add(power3);

    }

}