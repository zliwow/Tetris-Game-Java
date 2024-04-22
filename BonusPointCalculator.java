/*
 * Zihao Li CS 5004
 * Attempt to incorperate Lambda Expression
 * Also include use of filter and map
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class BonusPointCalculator {
    private List<Predicate<Integer>> bonusConditions;

    public BonusPointCalculator() {
        bonusConditions = Arrays.asList(
            linesCleared -> linesCleared >= 4,    // Bonus for clearing 4 or more lines at once
            linesCleared -> linesCleared == 3,    // Bonus for clearing 3 lines at once
            linesCleared -> linesCleared == 2     // Bonus for clearing 2 lines at once
        );
    }

    public int calculateBonusPoints(int linesCleared) {
        return bonusConditions.stream()
            .filter(condition -> condition.test(linesCleared)) // Filter method selected the condition with the number of lines
            .mapToInt(condition -> { // MapToInt method maps each condition to the corresponding bonus points. 
                if (condition.test(4)) {
                    return 1000;  // Bonus points for clearing 4 or more lines
                } else if (condition.test(3)) {
                    return 500;   // Bonus points for clearing 3 lines
                } else if (condition.test(2)) {
                    return 200;   // Bonus points for clearing 2 lines
                } else {
                    return 0;
                }
            })
            .sum(); // Sum the total points
    }
}