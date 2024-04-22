public class OTetromino extends Tetromino {
    private static final int[][] SHAPE = {
        {1, 1},
        {1, 1}
    };

    public OTetromino() {
        super(SHAPE);
    }

    @Override
    public Tetromino rotate() {
        return this; // O tetromino does not rotate
    }
}