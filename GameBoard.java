/*
 * Zihao Li CS 5004
 * Handles movement check, tetromino placements and line clear
 * Lineclear function is a use of recursion, to fulfill the project requirement
 * Atempt to use linkedlist to fulfill the project requirement
 */

import java.util.LinkedList;

public class GameBoard {
    // row ad colums to store the dimensions of the game board
    private int rows;
    private int columns;
    // grid is a 2d array that represents the game board
    private int[][] grid;
    // using linkedlist to store row indices in each column
    private LinkedList<Integer>[] stacks;

    // constructor intialize the variables and create a new linkedlist
    public GameBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new int[rows][columns];
        this.stacks = new LinkedList[columns];
        for (int i = 0; i < columns; i++) {
            stacks[i] = new LinkedList<>();
        }
    }

    // making sure the tetromino doesn't move out of the grid
    public boolean isValidMove(Tetromino tetromino, int deltaX, int deltaY) {
        int[][] shape = tetromino.getShape();
        int x = tetromino.getX() + deltaX;
        int y = tetromino.getY() + deltaY;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int newX = x + j;
                    int newY = y + i;
                    if (newX < 0 || newX >= columns || newY >= rows || grid[newY][newX] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // iterate and update tetromino's shape
    // push row index of non empty cell to the stack
    public void placeTetromino(Tetromino tetromino) {
        int[][] shape = tetromino.getShape();
        int x = tetromino.getX();
        int y = tetromino.getY();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    grid[y + i][x + j] = shape[i][j];
                    stacks[x + j].push(y + i);
                }
            }
        }
    }

    // An attempt to use recursion
    // In each for loop, it checks if the line is full, if full, it clears the line by calling itself, shiftLines down
    // returned value is used to calculate final score 
    public int clearLines() {
        int linesCleared = 0;
        for (int i = rows - 1; i >= 0; i--) {
            if (isLineFull(i)) {
                linesCleared++;
                clearLine(i);
                shiftLinesDown(i);
            }
        }
        return linesCleared;
    }

    // check if line is full
    private boolean isLineFull(int row) {
        for (int i = 0; i < columns; i++) {
            if (grid[row][i] == 0) {
                return false;
            }
        }
        return true;
    }

    // changing the value in the 2d array to 0 making it empty for future tetrominos
    private void clearLine(int row) {
        for (int i = 0; i < columns; i++) {
            grid[row][i] = 0;
            stacks[i].remove(Integer.valueOf(row));
        }
    }

    // copy the contents of the row above(i-1) to current row(i)
    private void shiftLinesDown(int startRow) {
        for (int i = startRow - 1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] != 0) {
                    grid[i + 1][j] = grid[i][j];
                    grid[i][j] = 0;
                    stacks[j].remove(Integer.valueOf(i));
                    stacks[j].push(i + 1);
                }
            }
        }
    }
    public int[][] getGrid() {
        return grid;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
