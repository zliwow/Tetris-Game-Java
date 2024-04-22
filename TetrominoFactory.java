/*
 * Zihao Li CS 5004
 * Inspired by previous lab works, in this case, TetrominoFactory is used to generate new piece
 */
import java.util.Random;

public class TetrominoFactory {
    private static final Random random = new Random();

    public static Tetromino createRandomTetromino() {
        int randomNumber = random.nextInt(7);
        switch (randomNumber) {
            case 0:
                return new ITetromino();
            case 1:
                return new OTetromino();
            case 2:
                return new LTetromino();
            case 3:
                return new JTetromino();
            case 4:
                return new STetromino();
            case 5:
                return new TTetromino();
            case 6:
                return new ZTetromino();
            default:
                return new ITetromino();
        }
    }
}