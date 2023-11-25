
package com.project.group13.frontend.view;

import com.project.group13.backend.handler.InputHandler;

import javax.swing.*;
import java.awt.*;

/**
 * BaseView serves as the superclass for all panel views within the game.
 * It extends JPanel to utilize the Swing framework for GUI components. This class
 * encapsulates common properties and behaviors such as frame and input handling.
 * It provides multiple constructors for flexibility in initialization and methods
 * to manage the JFrame's visibility and state.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class BaseView extends JPanel {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    private final JFrame frame;
    private final InputHandler inputHandler;

    /**
     * Constructs new base view with the
     * default jFrame
     */
    public BaseView() {
        this(new JFrame());
    }

    /**
     * Constructs new base view with the
     * given jFrame and a default input handler
     * @param jFrame JFrame object
     */
    public BaseView(JFrame jFrame) {
        this(jFrame, new InputHandler());
    }

    /**
     * Constructs new base view with the
     * given input handler and a default JFrame
     * @param inputHandler InputHandler object
     */
    public BaseView(InputHandler inputHandler) {
        this(new JFrame(), inputHandler);
    }

    /**
     * Constructs the base view with the given
     * jFrame and inputHandler
     * @param jFrame JFrame object
     * @param inputHandler InputHandler object
     */
    public BaseView(JFrame jFrame, InputHandler inputHandler) {
        this.frame = jFrame;
        this.inputHandler = inputHandler;
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /************************** Functions **************************/

    /**
     * Do this if jFrame is not setup before
     * Steps to follow:
     * On not initialized JFrame:
     *     Call initJFrame(), then showJFrame()
     * On previously initialized JFrame on this JPanel:
     *     Call showJFrame() directly
     * On previously initialized JFrame on different JPanel:
     *     Call revalidateJFrame(), then showJFrame()
     */
    public void initJFrame() {
        SwingUtilities.invokeLater(()->{
            frame.setTitle("Maze Runner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.addKeyListener(inputHandler);
            frame.addMouseListener(inputHandler);
            frame.addMouseMotionListener(inputHandler);
            frame.getContentPane().add(this);
            frame.pack();
            frame.setLocationRelativeTo(null); // centers the Frame
        });
    }

    /**
     * Revalidates the JFrame with this JPanel.
     * Only call this if the JFrame is initialized with a different panel
     * Hide your JFrame before calling it
     */
    public void revalidateJFrame() {
        SwingUtilities.invokeLater(()->{
            frame.getContentPane().removeAll();
            frame.getContentPane().add(this);
            frame.revalidate();
            frame.repaint();
            frame.addKeyListener(inputHandler);
            frame.addMouseListener(inputHandler);
            frame.addMouseMotionListener(inputHandler);
            frame.pack();
            frame.setLocationRelativeTo(null); // centers the Frame
        });
    }

    /**
     * Shows the JFrame
     */
    public void showJFrame() {
        frame.setVisible(true);
    }

    /**
     * Hides the JFrame
     */
    public void hideJFrame() {
        frame.setVisible(false);
    }

    /**
     * Closes the JFrame
     */
    public void disposeJFrame() {
        frame.dispose();
    }

    /************************ Getter/Setter ************************/

    /**
     * Gets the JFrame of this JPanel
     * @return JFrame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the input handler implemented on this JFrame
     * @return Input Handler
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

}
