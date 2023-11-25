
package com.project.group13.backend;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.util.Objects;

/**
 * BGM (Background Music) class extends Thread, allowing music files to be played in a separate thread.
 * This allows the game's background music to run in parallel with the game, without causing any delay in game
 * performance. It provides methods to start and stop the music.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class BGM extends Thread {

    String filepath; // Path to the file that will be played as background music.
    private Sequencer sequencer; // Sequencer for playing.

    /**
     * Constructor for BGM that sets the file path for the file to be played.
     * 
     * @param fpath The relative path to the file.
     */
    public BGM(String fpath) {
        filepath = fpath; // Set the file path for the background music file.
    }

    /**
     * Stops the background music playback.
     */
    public void stopBGM() {
        sequencer.stop(); // Stop the sequencer if it's currently running.
    }

    /**
     * The run method of the thread, called when the thread starts. It initializes the sequencer,
     * sets up the file for playback, and starts the sequencer.
     */
    @Override
    public void run() {
        try {
            // create a sequencer for playing MIDI files
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // create a sequence from the MIDI file
            Sequence sequence = MidiSystem.getSequence(Objects.requireNonNull(BGM.class.getResourceAsStream(filepath)));

            // set the sequence for the sequencer to play
            sequencer.setSequence(sequence);

            // start playing the MIDI file in the background
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();

        } catch (Exception ex) {
            ex.printStackTrace(); // Print any exceptions to standard error.
        }
    }

}
