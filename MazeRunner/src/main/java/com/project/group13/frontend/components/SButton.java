package com.project.group13.frontend.components;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Styled button interface
 */
public interface SButton {
    public enum BtnState {
        IDLE, HOVER, DOWN
    }
    public void setActionListener(ActionListener listener);
    public void act();
    public Rectangle getBounds();
    public Image getImage();
    public String getText();
    public boolean isInBounds(int x, int y);
    public void setState(BtnState state);
    public BtnState getState();
    public void setText(String text);
}