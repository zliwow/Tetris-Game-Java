/*
 * Zihao Li CS 5004
 * Controller that handles user input and update the game state
 */
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class GameController {
    private GameLoop gameLoop;
    private GameView gameView;
    private ScoreCalculator scoreCalculator;
    private HighScoreManager<Integer> highScoreManager;
    private Timer timer;

    public GameController(GameLoop gameLoop, GameView gameView) {
        this.gameLoop = gameLoop;
        this.gameView = gameView;
        this.scoreCalculator = new ScoreCalculatorImpl();
        this.highScoreManager = new HighScoreManager<>();
    }

    public void startGame() {
        gameView.setFocusable(true);
        gameView.requestFocus();
        gameView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameLoop.update();
                gameView.repaint();
                if (gameLoop.isGameOver()) {
                    timer.cancel();
                    showGameOver();
                }
            }
        }, 0, 500);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            gameLoop.moveLeft();
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameLoop.moveRight();
        } else if (event.getKeyCode() == KeyEvent.VK_UP) {
            gameLoop.rotate();
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            gameLoop.moveDown();
        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            gameLoop.hardDrop();
        }
    }

    private void showGameOver() {
        int score = gameLoop.getScore();
        highScoreManager.addScore(score);
        List<Integer> highScores = highScoreManager.getHighScores();
        int highScore = highScores.isEmpty() ? 0 : highScores.get(0);

        GameOverScreen gameOverScreen = new GameOverScreen(score, highScore);
        gameOverScreen.setPreferredSize(gameView.getPreferredSize());

        Frame frame = (Frame) gameView.getParent();
        frame.remove(gameView);
        frame.add(gameOverScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}