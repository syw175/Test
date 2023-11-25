
package com.project.group13.backend;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * The Sound class extends Thread and handles the playing of sound files. It supports looping and playing
 * a sound file once. The sounds are meant to be played in a separate thread to avoid blocking the game's
 * main thread, allowing the game and sound to run concurrently.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Sound extends Thread {

    String filepath; // The path to the sound file.
    boolean loop = false; // A boolean variable to determine if the sound should loop continuously.

    /**
     * Constructor for the Sound class.
     * 
     * @param fpath The relative path to the sound file to play.
     */
    public Sound(String fpath) {
        filepath = fpath; // Assigning the file path to the filepath variable.
    }

    /**
     * Sets the looping behavior for the sound.
     * 
     * @param val If true, the sound will loop continuously; otherwise, it will not loop.
     */
    public void setLoop(boolean val) {
        this.loop = val; // Setting the loop variable to the passed value.
    }

    /**
     * The overridden run method from the Thread class that plays the sound. If the loop flag is set,
     * the sound will loop until the thread is interrupted; otherwise, it will play once and stop.
     */
    @Override
    public void run() {
        try {
            // Acquire an input stream associated with the file path stored in the instance variable.
            InputStream inputStream = Sound.class.getResourceAsStream(filepath);
            // Wrapping the input stream with a BufferedInputStream for efficient reading.
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            // Getting an AudioInputStream from the buffered input stream for sound processing.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            // Acquiring the audio format from the audio input stream to describe the sound data format.
            AudioFormat format = audioInputStream.getFormat();

            // Preparing the DataLine.Info object with format details, which is required to get a line that matches the description.
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            // Getting a Clip that can be used to play an audio file. It is a special kind of data line that can be loaded with audio data.
            Clip clip = (Clip) AudioSystem.getLine(info);
            // Opening the clip with the audio input stream loaded into it.
            clip.open(audioInputStream);

            // If loop is true, start looping the clip continuously.
            if (loop)
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Start playing the sound.
            clip.start();
            // A short sleep duration to ensure the clip has time to begin playing before continuing.
            Thread.sleep(100);

            // Looping condition to keep checking if the sound is still running or if the loop flag is set.
            while (clip.isRunning() || loop) {
                // Sleeps to avoid heavy CPU usage in the while loop.
                Thread.sleep(100);
            }

            // Once finished playing or interrupted, close the clip to release resources.
            clip.close();

        } catch (Exception e) {
            e.printStackTrace(); // Print any exceptions to standard error.
        }
    }
}
