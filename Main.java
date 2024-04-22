import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(20, 10);
        GameView gameView = new GameView(gameBoard);
        GameLoop gameLoop = new GameLoop(gameBoard, gameView);
        GameController gameController = new GameController(gameLoop, gameView);

        Frame frame = new Frame("Tetris");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.add(gameView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gameController.startGame();
    }
}