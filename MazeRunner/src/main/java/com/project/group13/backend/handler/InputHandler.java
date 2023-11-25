
package com.project.group13.backend.handler;

import java.awt.*;
import java.awt.event.*;

/**
 * InputHandler class implements KeyListener, MouseListener, and MouseMotionListener interfaces
 * to provide a handling mechanism for keyboard and mouse events. It serves as a base class
 * that other specific handlers can extend to override the necessary methods for event processing.
 * The empty implementations provided here act as a convenience so that child classes only need to
 * override methods they are interested in.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    /**
     * Removes all registered listeners
     */
    public void removeAllListeners(Component component) {
        for (KeyListener listener : component.getKeyListeners()) {
            component.removeKeyListener(listener);
        }
        for (MouseListener listener : component.getMouseListeners()) {
            component.removeMouseListener(listener);
        }
        for (MouseMotionListener listener : component.getMouseMotionListeners()) {
            component.removeMouseMotionListener(listener);
        }
    }

}