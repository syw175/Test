
package com.project.group13.backend.model.levels;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.*;

/**
 * Level3 implements the Level interface and defines the specific layout and initialization for the first level
 * of the game. It initializes the map layout, places rewards and enemies, and sets their behavior for this level.
 * The layout is represented by a matrix where different integers represent different types of tiles or spaces.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Level3 implements Level {

    private final GameMap gameMap; // The game map specific to Level 2.

    /**
     * Constructs game map for level 3 (Similar to Level 1 and 2)
     */
    public Level3() {
        final int[][] map = new int[][]{
                {0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0},
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
        LifeReward lifeReward = new LifeReward(new Position(23, 1), 1);
        LifeReward lifeReward2 = new LifeReward(new Position(11, 17), 1);

        // place health rewards
        HealthReward healthReward = new HealthReward(new Position(9, 17));
        HealthReward healthReward2 = new HealthReward(new Position(12, 7));
        HealthReward healthReward3 = new HealthReward(new Position(15, 17));

        // add rewards
        model.getRewards().add(lifeReward);
        model.getRewards().add(lifeReward2);

        model.getRewards().add(healthReward);
        model.getRewards().add(healthReward2);
        model.getRewards().add(healthReward3);

        // place static enemies
        SpikeBall spikeBall = new SpikeBall(new Position(18, 12), 8);
        SpikeBall spikeBall2 = new SpikeBall(new Position(9, 8), 6);
        SpikeBall spikeBall3 = new SpikeBall(new Position(17, 4), 9);
        SpikeBall spikeBall4 = new SpikeBall(new Position(5, 4), 2);
        SpikeBall spikeBall5 = new SpikeBall(new Position(2, 16), 5);

        // place moving normal enemies
        Spikes spikes = new Spikes(new Position(22, 5), 1, Axis.Y_AXIS, Directions.LEFT, 250);
        Spikes spikes2 = new Spikes(new Position(3, 8), 5, Axis.Y_AXIS, Directions.UP, 50);
        Spikes spikes3 = new Spikes(new Position(15, 15), 2, Axis.X_AXIS, Directions.UP, 200);
        Spikes spikes4 = new Spikes(new Position(3, 12), 2, Axis.X_AXIS, Directions.UP, 50);

        // add enemies
        model.getEnemies().add(spikeBall);
        model.getEnemies().add(spikeBall2);
        model.getEnemies().add(spikeBall3);
        model.getEnemies().add(spikeBall4);
        model.getEnemies().add(spikeBall5);

        model.getBarriers().add(spikes);
        model.getBarriers().add(spikes2);
        model.getBarriers().add(spikes3);
        model.getBarriers().add(spikes4);

        // place special items
        PowerReward power = new PowerReward(new Position(7, 1));
        PowerReward power1 = new PowerReward(new Position(1, 9));
        PowerReward power2 = new PowerReward(new Position(12, 9));
        PowerReward power3 = new PowerReward(new Position(18, 14));

        model.getRewards().add(power);
        model.getRewards().add(power1);
        model.getRewards().add(power2);
        model.getRewards().add(power3);


    }

}