
package com.project.group13.backend;

import com.project.group13.backend.ctrl.CtrlFactory;

/**
 * The Game class serves as the launching point for the game. It initializes the necessary control
 * structures and begins playing background music. The class is designed to be called from the main
 * method and sets up the game environment, including the menu system and sound.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Game {

    /**
     * The constructor for the Game class initializes the game control and sound management.
     * It uses the CtrlFactory to create and show the menu control, which is the starting view of the game.
     * It also starts the background music which continues to play throughout the game.
     */
    public Game() {
        // Create and display the main menu control.
        CtrlFactory.createMenuCtrlInstance(); // Initializes and shows the menu view.

        // Start playing the background music.
        SoundManager.BGM(); // Plays background music continuously.
    }

}