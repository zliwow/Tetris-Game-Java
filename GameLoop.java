public class GameLoop {
    private GameBoard gameBoard;
    private GameView gameView;
    private Tetromino currentTetromino;
    private boolean gameOver;
    private int score;
    private BonusPointCalculator bonusPointCalculator;

    public GameLoop(GameBoard gameBoard, GameView gameView) {
        this.gameBoard = gameBoard;
        this.gameView = gameView;
        this.gameOver = false;
        this.score = 0;
        this.bonusPointCalculator = new BonusPointCalculator();
        spawnTetromino();
    }

    public void update() {
        
        if (!gameOver) {
            if (gameBoard.isValidMove(currentTetromino, 0, 1)) {
                currentTetromino.moveDown();
            } else {
                gameBoard.placeTetromino(currentTetromino);
                int clearedLines = gameBoard.clearLines();
                score += calculateScore(clearedLines);
                score += bonusPointCalculator.calculateBonusPoints(clearedLines);
                spawnTetromino();
                
                if (!gameBoard.isValidMove(currentTetromino, 0, 0)) {
                    gameOver = true;
                }
            }
            gameView.setCurrentTetromino(currentTetromino);
        }
    }

    private void spawnTetromino() {
        currentTetromino = TetrominoFactory.createRandomTetromino();
        currentTetromino.setX(gameBoard.getColumns() / 2);
        currentTetromino.setY(0);

        if (!gameBoard.isValidMove(currentTetromino, 0, 0)) {
            gameOver = true;
        }
    }

    public void moveLeft() {
        if (gameBoard.isValidMove(currentTetromino, -1, 0)) {
            currentTetromino.moveLeft();
        }
    }

    public void moveRight() {
        if (gameBoard.isValidMove(currentTetromino, 1, 0)) {
            currentTetromino.moveRight();
        }
    }

    public void moveDown() {
        if (gameBoard.isValidMove(currentTetromino, 0, 1)) {
            currentTetromino.moveDown();
        }
    }

    public void hardDrop() {
        while (gameBoard.isValidMove(currentTetromino, 0, 1)) {
            currentTetromino.moveDown();
        }
        gameBoard.placeTetromino(currentTetromino);
        int clearedLines = gameBoard.clearLines();
        score += calculateScore(clearedLines);
        spawnTetromino();

        if (!gameBoard.isValidMove(currentTetromino, 0, 0)) {
            gameOver = true;
        }
    }

    public void rotate() {
        Tetromino rotatedTetromino = currentTetromino.rotateInPlace();
        if (gameBoard.isValidMove(rotatedTetromino, 0, 0)) {
            currentTetromino = rotatedTetromino;
        } else {
            // Revert the rotation if the rotated position is not valid
            rotatedTetromino.rotateInPlace();
            rotatedTetromino.rotateInPlace();
            rotatedTetromino.rotateInPlace();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    private int calculateScore(int linesCleared) {
        return new ScoreCalculatorImpl().calculateScore(linesCleared);
    }
}
