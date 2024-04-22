/*
 * Zihao Li CS 5004
 * This class handles game rendering
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameView extends Canvas {
    private static final int CELL_SIZE = 30;
    private GameBoard gameBoard;
    private Tetromino currentTetromino;

    public GameView(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        setPreferredSize(new Dimension(gameBoard.getColumns() * CELL_SIZE, gameBoard.getRows() * CELL_SIZE));
    }

    @Override
    public void paint(Graphics g) {
        render(g);
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw grid
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= gameBoard.getRows(); i++) {
            g.drawLine(0, i * CELL_SIZE, gameBoard.getColumns() * CELL_SIZE, i * CELL_SIZE);
        }
        for (int j = 0; j <= gameBoard.getColumns(); j++) {
            g.drawLine(j * CELL_SIZE, 0, j * CELL_SIZE, gameBoard.getRows() * CELL_SIZE);
        }

        // Draw placed tetrominoes
        int[][] grid = gameBoard.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    drawBlock(g, j, i, Color.BLUE);
                }
            }
        }

        // Draw current falling tetromino
        if (currentTetromino != null) {
            int[][] shape = currentTetromino.getShape();
            int x = currentTetromino.getX();
            int y = currentTetromino.getY();

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        drawBlock(g, x + j, y + i, Color.RED);
                    }
                }
            }
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.WHITE);
        g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void setCurrentTetromino(Tetromino tetromino) {
        this.currentTetromino = tetromino;
    }
}