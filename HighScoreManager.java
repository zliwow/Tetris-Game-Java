/*
 * Zihao Li CS 5004
 * Attempt to incorperate generics to fulfill project requirement
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Attempt to use generics here to allow highscoremanager to work with different types of scores
// the custom scores could be for example integer or long, one could create the instance of the custom score by using HighScoreManager<Integer>, HighScoreManager<Long>
public class HighScoreManager<T extends Comparable<T>> { 
    private static final String HIGH_SCORES_FILE = "high_scores.txt";
    private static final int MAX_HIGH_SCORES = 10;
    // The list to store the high scores
    private List<T> highScores;

    // Constructor initializes the high scores by loading them from file
    public HighScoreManager() {
        highScores = loadHighScores();
    }

    // Adds a new score to the high scores list
    // Sorts the scores in descending order and removes excess scores beyond the maximum limit
    public void addScore(T score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > MAX_HIGH_SCORES) {
            highScores.remove(MAX_HIGH_SCORES);
        }
        saveHighScores();
    }

    public List<T> getHighScores() {
        return highScores;
    }

    // Loads the high scores from file
    // Reads each line from the file and parses it into a score of type T
    // Returns the list of loaded high scores
    private List<T> loadHighScores() {
        List<T> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(parseScore(line));
            }
        } catch (IOException e) {
            // Handle file reading error
        }
        return scores;
    }

    // Saves the high scores to file
    // Writes each score to the file using its toString representation
    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORES_FILE))) {
            for (T score : highScores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file writing error
        }
    }

    // Parses a score from its string representation to an object of type T
    @SuppressWarnings("unchecked")
    private T parseScore(String line) {
        return (T) Integer.valueOf(line);
    }
}