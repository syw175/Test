
package com.project.group13.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The LeaderboardManager class provides a static interface for managing the game's leaderboard.
 * It maintains a list of top scores, ensures that the list doesn't exceed a set maximum, and allows
 * retrieval of the current top scores. The leaderboard is sorted in descending order so that higher scores
 * appear first.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LeaderboardManager {

    private static final int MAX_SCORES = 10; // Maximum number of scores to keep in the leaderboard.
    private static List<Integer> scores = new ArrayList<>(); // List to store the top scores.

    /************************** Functions **************************/

    /**
     * Adds a new score to the leaderboard. The leaderboard is sorted in descending order after adding the score.
     * If the leaderboard exceeds the maximum size, it trims the lowest score.
     * 
     * @param score The score to add to the leaderboard.
     */
    public static void addScore(int score) {
        scores.add(score); // Add the new score to the list.
        scores.sort(Collections.reverseOrder()); // Sort the list in descending order.
        trimToMaxScores(); // Trim the list to the maximum allowed scores.
    }

    /**
     * Retrieves a list of the top scores. The returned list is a copy to prevent modification of the original list.
     * 
     * @return A list of top scores, sorted in descending order.
     */
    public static List<Integer> getTopScores() {
        return new ArrayList<>(scores); // Return a new ArrayList containing all the scores to avoid external changes.
    }

    /**
     * Trims the scores list to contain only the top N scores as defined by MAX_SCORES.
     * This method is called internally to enforce the maximum size constraint.
     */
    private static void trimToMaxScores() {
        // If the list size exceeds MAX_SCORES, remove the lowest scores.
        while (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1); // Remove the last score in the list (the lowest due to sorting order).
        }
    }

}
