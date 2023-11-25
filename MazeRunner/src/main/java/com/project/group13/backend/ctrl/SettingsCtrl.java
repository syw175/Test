
package com.project.group13.backend.ctrl;

import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.SButton;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.components.StyledToggleBtn;
import com.project.group13.frontend.view.BaseView;
import com.project.group13.frontend.view.SettingsView;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * SettingsCtrl extends InputHandler to provide control for the settings view. It initializes
 * the SettingsView with the necessary configurations for user interaction. This controller
 * is responsible for handling user inputs within the settings screen and applying any changes
 * such as toggling sound or music on or off.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SettingsCtrl extends InputHandler {

    private final SettingsView settingsView;

    /**
     * Constructs the settings controller, with default objects
     */
    public SettingsCtrl() {
        this.settingsView = new SettingsView(this);
    }

    /**
     * Constructs the settings controller with given JFrame
     */
    public SettingsCtrl(JFrame jFrame) {
        this.settingsView = new SettingsView(jFrame, this);
    }

    /************************** Handlers **************************/

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        SButton[] buttonList = settingsView.getButtonList();

        boolean redrawNeeded = false;

        for (SButton sb : buttonList) {
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
            settingsView.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        SButton[] buttonList = settingsView.getButtonList();

        for (SButton sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.DOWN);
                settingsView.repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY()-(BaseView.GAME_HEIGHT/16);

        SButton[] buttonList = settingsView.getButtonList();

        for (SButton sb : buttonList) {
            if (sb.isInBounds(x, y)) {
                sb.setState(StyledBtn.BtnState.HOVER);
                settingsView.repaint();
                if (sb instanceof StyledBtn)
                    this.removeAllListeners(settingsView.getFrame());
                sb.act();
            }
        }
    }

}