/*
 * Zihao Li Cs 5004
 * Using abstract class as the project require
 */

// abstract class is used here to give shared properties and behavior for all tetromino shapes
public abstract class Tetromino {
    protected int[][] shape;
    protected int x;
    protected int y;

    public Tetromino(int[][] shape) {
        this.shape = shape;
    }

    public abstract Tetromino rotate();

    // rotating the tetromino in place
    // recreate the new piece after rotating
    // Row index in newShape: j Column index in newShape: shape.length - 1 - i,  effectively rotates the shape by 90 degrees clockwise
    /*
     * Taking the T piece for example
     * Original shape:
        [1, 1, 1]
        [0, 1, 0]

        Rotated shape:
        [0, 1]
        [1, 1]
        [0, 1]
     */
    public Tetromino rotateInPlace() {
        int[][] newShape = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                newShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = newShape;
        return this;
    }

    // handles movements by changing the cordinate 
    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}