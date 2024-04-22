public class ITetromino extends Tetromino {
    private static final int[][] SHAPE = {
        {1, 1, 1, 1}
    };

    public ITetromino() {
        super(SHAPE);
    }

    @Override
    public Tetromino rotate() {
        int[][] newShape = new int[4][1];
        for (int i = 0; i < 4; i++) {
            newShape[i][0] = shape[0][3 - i];
        }
        return new ITetromino(newShape);
    }

    private ITetromino(int[][] shape) {
        super(shape);
    }
}