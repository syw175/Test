package com.project.group13.frontend.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Helps in adding image button to the gui
 */
public class StyledBtn implements SButton {

    private final Rectangle bounds;
    private BtnState curr_state;
    private final String btn_text;
    private ActionListener actionListener = null;

    /**
     * Creates styled button
     * @param btnText text for the button
     * @param bounds pass button bounds
     */
    public StyledBtn(String btnText, Rectangle bounds) {
        this.bounds = bounds;
        this.curr_state = BtnState.IDLE;
        this.btn_text = btnText;
    }

    /********************** Functions/Getters/Setters ***********************/

    /**
     * Gets button bounds
     * @return rectangle bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Checks if the given coordinate is in bounds
     * @param x x position
     * @param y y position
     * @return true if is in bounds otherwise false
     */
    public boolean isInBounds(int x, int y) {
        return x >= bounds.getX() && x <= bounds.getX()+bounds.getWidth() && y >= bounds.getY() && y <= bounds.getY()+bounds.getHeight();
    }

    /**
     * Gets button state
     * @return button state
     */
    public BtnState getState() {
        return curr_state;
    }

    /**
     * Sets the current button state
     * @param state button state
     */
    public void setState(BtnState state) {
        this.curr_state = state;
    }

    /**
     * returns background image for the button based upon the current state
     * @return background image
     */
    public Image getImage() {
        return switch (curr_state) {
            case IDLE -> BtnRes.IDLE;
            case HOVER -> BtnRes.HOVER;
            case DOWN -> BtnRes.DOWN;
            default -> null;
        };
    }

    /**
     * returns button text
     * @return text
     */
    public String getText() {
        return btn_text;
    }

    /**
     * sets the text of the button
     * @param text button text
     */
    public void setText(String text) {}

    /**
     * sets action for this button
     * @param listener action listener
     */
    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    /**
     * Performs action designated to this button
     */
    public void act() {
        if (actionListener != null) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"));
        }
    }

}