
package com.project.group13.backend.ctrl;

import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.view.BaseView;
import com.project.group13.frontend.view.LeaderboardView;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * LeaderboardCtrl extends InputHandler to manage the interactions specific to the leaderboard.
 * It creates and associates a LeaderboardView with this controller to display the game's leaderboard.
 * This controller is responsible for initializing the view.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LeaderboardCtrl extends InputHandler {

    private final LeaderboardView leaderboardView;

    /**
     * Constructs the leaderboard controller, with default objects
     */
    public LeaderboardCtrl() {
        this.leaderboardView = new LeaderboardView(this);
    }

    /**
     * Constructs the leaderboard controller with given JFrame
     */
    public LeaderboardCtrl(JFrame jFrame) {
        this.leaderboardView = new LeaderboardView(jFrame, this);
    }

    /************************** Handlers **************************/

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = leaderboardView.getButtonList();

        boolean redrawNeeded = false;

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.HOVER);
                redrawNeeded = true;
            } else {
                if (sb.getState() == StyledBtn.BtnState.HOVER) {
                    sb.setState(StyledBtn.BtnState.IDLE);
                    redrawNeeded = true;
                }
            }
        }

        if (redrawNeeded)
            leaderboardView.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = leaderboardView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.DOWN);
                leaderboardView.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = leaderboardView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.HOVER);
                leaderboardView.repaint();
                this.removeAllListeners(leaderboardView.getFrame());
                sb.act();
            }
        }
    }

}