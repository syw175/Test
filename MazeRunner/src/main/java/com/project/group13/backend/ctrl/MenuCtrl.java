
package com.project.group13.backend.ctrl;

import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.view.BaseView;
import com.project.group13.frontend.view.MenuView;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * MenuCtrl extends InputHandler to provide control logic for the menu view. It initializes
 * the MenuView and sets up the interaction mechanisms for the menu's buttons and options.
 * This controller is responsible for reacting to user inputs and triggering transitions to
 * other parts of the game such as starting a new game, opening the leaderboard, accessing
 * settings, and exiting the game.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class MenuCtrl extends InputHandler {

    private final MenuView menuView;

    /**
     * Constructs the menu controller, with default objects
     */
    public MenuCtrl() {
        this.menuView = new MenuView(this);
    }

    /**
     * Constructs the menu controller with given JFrame
     */
    public MenuCtrl(JFrame jFrame) {
        this.menuView = new MenuView(jFrame, this);
    }

    /************************** Handlers **************************/

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = menuView.getButtonList();

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
            menuView.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = menuView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.DOWN);
                menuView.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        StyledBtn[] buttonList = menuView.getButtonList();

        for (StyledBtn sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.HOVER);
                menuView.repaint();
                this.removeAllListeners(menuView.getFrame());
                sb.act();
            }
        }
    }

}
