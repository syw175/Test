
package com.project.group13.backend;

/**
 * The SoundManager class is responsible for managing and playing all the sounds within the game.
 * It controls sound effects and background music based on static flags that can be toggled by the game settings.
 * The sound effects include actions like dying, collecting items, taking damage, winning, and clicking.
 * Background music management is started or stopped through this class as well.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SoundManager {

    public static boolean SOUND_ON = true; // Flag indicating whether sound effects are enabled.
    public static boolean MUSIC_ON = true; // Flag indicating whether background music is enabled.
    public static BGM bgm = null; // Reference to the background music thread.

    /*************************** Functions ***************************/

    /**
     * Toggle sound value
     */
    public static void toggleSound() {
        SOUND_ON = !SOUND_ON;
    }

    /**
     * Toggle music value
     */
    public static void toggleMusic() {
        MUSIC_ON = !MUSIC_ON;
        BGM();
    }

    /**
     * Plays or stops the bgm according to the MUSIC_ON value
     */
    public static void BGM() {
        if (MUSIC_ON) {
            SoundManager.bgm = new BGM("/audio/bgm.mid");
            SoundManager.bgm.start();
        } else {
            if (SoundManager.bgm != null)
                SoundManager.bgm.stopBGM();
        }
    }

    /**
     * Plays the die sound
     */
    public static void playDieSound() {
        if (SOUND_ON) {
            Sound s = new Sound("/audio/game_lost.wav");
            s.start();
        }
    }

    /**
     * Plays the item collect sound
     */
    public static void playCollectSound() {
        if (SOUND_ON) {
            Sound s = new Sound("/audio/collect.wav");
            s.start();
        }
    }

    /**
     * Plays the damage sound
     */
    public static void playDamageSound() {
        if (SOUND_ON) {
            Sound s = new Sound("/audio/hurt.wav");
            s.start();
        }
    }

    /**
     * Plays the win sound
     */
    public static void playWinSound() {
        if (SOUND_ON) {
            Sound s = new Sound("/audio/game_win.wav");
            s.start();
        }
    }

    /**
     * Plays the click sound
     */
    public static void playClickSound() {
        if (SOUND_ON) {
            Sound s = new Sound("/audio/click_treble.wav");
            s.start();
        }
    }

}
