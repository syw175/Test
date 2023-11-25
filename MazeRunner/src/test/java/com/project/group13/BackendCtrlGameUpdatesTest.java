package com.project.group13;

import org.junit.Before;
import org.junit.Test;

import com.project.group13.backend.BackendCtrl;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.gameobjects.Barrier;
import com.project.group13.backend.model.gameobjects.Enemy;
import com.project.group13.backend.model.gameobjects.Reward;
import com.project.group13.backend.model.levels.Level;

import static org.mockito.Mockito.*;

import java.util.List;

public class BackendCtrlGameUpdatesTest {
    private BackendCtrl backendCtrl;
    private GameModel mockGameModel;
    private List<Reward> mockRewards;
    private List<Enemy> mockEnemies;
    private List<Barrier> mockBarriers;
    private Level mockLevel;


    @Before
    public void setUp() {
        mockGameModel = mock(GameModel.class);
        mockRewards = mock(List.class);
        mockEnemies = mock(List.class);
        mockBarriers = mock(List.class);
        mockLevel = mock(Level.class);
        backendCtrl = new BackendCtrl(mockGameModel);

        when (mockGameModel.getLevel()).thenReturn(mockLevel);
        when (mockLevel.getRewards()).thenReturn(mockRewards);
        when (mockLevel.getEnemies()).thenReturn(mockEnemies);
        when (mockLevel.getBarriers()).thenReturn(mockBarriers);
    }

    @Test
    public void testUpdateObjectsPosition() {
        backendCtrl.updateObjectsPosition();
        verify(mockGameModel).updateObjectsPosition();
    }





    
}
