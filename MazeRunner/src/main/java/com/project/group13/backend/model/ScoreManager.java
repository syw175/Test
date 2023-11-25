
package com.project.group13.backend.model;

/**
 * Manages score data
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class ScoreManager {

    private double score;
    private double totalScore;

    /**
     * Constructs score manager
     */
    public ScoreManager() {
        this.score = 0.0;
        totalScore = 0.0;
    }

    /************************ Getter/Setter ************************/

    /**
     * Adds a value to the score
     * @param val value to be added in the score
     */
    public void addScore(double val) {
        score += val;
    }

    /**
     * Gets score in the game
     * @return score
     */
    public int getScore() {
        return (int) score;
    }

    /**
     * resets the game score to zero
     */
    public void resetScore() {
        this.score = 0;
    }

    /**
     * Adds in total score
     * @param score score in total of all levels
     */
    public void addScoreInTotal(double score) {
        this.totalScore += score;
    }

    /**
     * Gets total score combining all levels
     * @return total score
     */
    public int getTotalScore() {
        return (int) totalScore;
    }

}
