import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOverScreen extends Canvas {
    private int score;
    private int highScore;

    public GameOverScreen(int score, int highScore) {
        this.score = score;
        this.highScore = highScore;
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 24));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Game Over", 100, 100);
        g.drawString("Final Score: " + score, 100, 150);
        g.drawString("High Score: " + highScore, 100, 200);
    }
}