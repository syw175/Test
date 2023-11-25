
package com.project.group13;

import com.project.group13.backend.Game;

/**
 * This is the entry point of the the game.
 * The main class is responsible for starting the game by
 * initializing the game's main logic contained within the Game class.
 * The game starts by calling the runGame method from the main method.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Main {

    /**
     * The entry point that is run from the command line.
     * This method delegates the game startup process to the runGame method.
     *
     * @param args command line arguments passed to the application, (This is general. Not used).
     */
    public static void main(String[] args) {
        new Main().runGame();
    }

    /**
     * The runGame method is responsible for the creation of a Game object which starts the game.
     */
    public void runGame() {
        new Game();
    }

}