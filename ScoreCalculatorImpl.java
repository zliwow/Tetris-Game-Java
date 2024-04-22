/*
 * Zihao Li CS 5004
 * Use of interface for project requirement
 */

// calculation logic for lines cleared, more lines makes more score
// this can be expanded in the future for other types of scoring such as combos, tspin, ngle and such
public class ScoreCalculatorImpl implements ScoreCalculator {
    @Override
    public int calculateScore(int linesCleared) {
        switch (linesCleared) {
            case 1:
                return 100;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 800;
            default:
                return 0;
        }
    }
}