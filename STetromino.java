public class STetromino extends Tetromino {
    private static final int[][] SHAPE = {
        {0, 1, 1},
        {1, 1, 0}
    };

    public STetromino() {
        super(SHAPE);
    }

    @Override
    public Tetromino rotate() {
        int[][] newShape = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                newShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        return new STetromino(newShape);
    }

    private STetromino(int[][] shape) {
        super(shape);
    }
}
