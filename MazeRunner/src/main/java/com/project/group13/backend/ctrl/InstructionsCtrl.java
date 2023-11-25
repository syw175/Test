package com.project.group13.backend.ctrl;

import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.view.BaseView;
import com.project.group13.frontend.view.InstructionsView;
import com.project.group13.frontend.view.LeaderboardView;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Handles instructions view
 */
public class InstructionsCtrl extends InputHandler {

    private final InstructionsView instructionsView;

    /**
     * Constructs the instructions controller, with default objects
     */
    public InstructionsCtrl() {
        this.instructionsView = new InstructionsView(this);
    }

    /**
     * Constructs the instructions controller with given JFrame
     */
    public InstructionsCtrl(JFrame jFrame) {
        this.instructionsView = new InstructionsView(jFrame, this);
    }

    /************************** Handlers **************************/

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = instructionsView.getButtonList();

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
            instructionsView.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = instructionsView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.DOWN);
                instructionsView.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = instructionsView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.HOVER);
                instructionsView.repaint();
                this.removeAllListeners(instructionsView.getFrame());
                sb.act();
            }
        }
    }

}