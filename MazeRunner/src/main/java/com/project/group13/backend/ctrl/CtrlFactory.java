
package com.project.group13.backend.ctrl;

import javax.swing.*;

/**
 * CtrlFactory is a utility class that provides static factory methods for creating instances of different
 * controllers used in the game. These controllers manage different parts of the game such as the menu, game,
 * leaderboard, and settings. The class allows for controllers to be created with or without a specified
 * JFrame context.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class CtrlFactory {

    /**
     * Creates menu controller instance
     */
    public static void createMenuCtrlInstance() {
        new MenuCtrl();
    }

    /**
     * Creates menu controller instance,
     * with the given JFrame
     */
    public static void createMenuCtrlInstance(JFrame jFrame) {
        new MenuCtrl(jFrame);
    }

    /**
     * Creates game controller instance
     */
    public static void createGameCtrlInstance() {
        new GameCtrl();
    }

    /**
     * Creates game controller instance,
     * with the given JFrame
     */
    public static void createGameCtrlInstance(JFrame jFrame) {
        new GameCtrl(jFrame);
    }

    /**
     * Creates leaderboard controller instance
     */
    public static void createLeaderboardCtrlInstance() {
        new LeaderboardCtrl();
    }

    /**
     * Creates leaderboard controller instance,
     * with the given JFrame
     */
    public static void createLeaderboardCtrlInstance(JFrame jFrame) {
        new LeaderboardCtrl(jFrame);
    }

    /**
     * Creates settings controller instance
     */
    public static void createSettingsCtrlInstance() {
        new SettingsCtrl();
    }

    /**
     * Creates settings controller instance,
     * with the given JFrame
     */
    public static void createSettingsCtrlInstance(JFrame jFrame) {
        new SettingsCtrl(jFrame);
    }

    /**
     * Creates instructions controller instance
     */
    public static void createInstructionsCtrlInstance() {new InstructionsCtrl();}

    /**
     * Creates instructions controller instance,
     * with the given JFrame
     */
    public static void createInstructionsCtrlInstance(JFrame jFrame) {new InstructionsCtrl(jFrame);}

}