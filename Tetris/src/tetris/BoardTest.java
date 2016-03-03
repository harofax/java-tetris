package tetris;
import javax.swing.*;
import java.awt.event.ActionEvent;

final class BoardTest {

    public static final int WIDTH = 14;
    public static final int HEIGHT = 20;
    private static final int DELAY = 500;

    public static Timer clockTimer;

    private BoardTest() {}

    public static void main(String[] args) {

	Board gameBoard = new Board(WIDTH, HEIGHT);
	TetrisFrame gameFrame = new TetrisFrame(gameBoard);
	HighscoreList highscore = HighscoreList.getInstance();

	final Action doOneStep = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
		if (gameBoard.isGameRunning()) {
		    gameBoard.tick();
		} else {
		    clockTimer.stop();
		    String name = JOptionPane.showInputDialog(null, "Enter name");
		    if (name == null || name.isEmpty()) {
			name = "N/A";
		    }
		    highscore.addHighscore(name, gameBoard.score);
		    highscore.sort();

		    gameFrame.showHighscore(highscore);

		}
	    }
	};

	clockTimer = new Timer(DELAY, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }


}
