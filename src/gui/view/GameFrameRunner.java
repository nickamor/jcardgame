package gui.view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

/**
 * Runs new GUI games.
 * 
 * @see GameFrame
 * @see GameFrameThread
 * @author Nicholas Amor
 *
 */
public class GameFrameRunner {
	/**
	 * Start a new game window with this engine.
	 * 
	 * @param engine
	 *            The engine to use.
	 */
	public static void newGame(final GameEngine engine) {
		Runnable newWindowRunner = new Runnable() {

			@Override
			public void run() {
				try {
					new GameFrame(engine);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		};

		SwingUtilities.invokeLater(newWindowRunner);
	}

	/**
	 * Start a new game window with the default engine.
	 */
	public static void newGame() {
		newGame(new GameEngineImpl());
	}

	/**
	 * Attempt to join a multiplayer game on a local server.
	 */
	public static void joinLocalGame() {
		Runnable joinDialogRunner = new Runnable() {

			@Override
			public void run() {
				JoinServerDialog dialog = new JoinServerDialog();
				dialog.setVisible(true);
				GameEngine engine = dialog.getValue();

				if (engine != null) {
					newGame(engine);
				}

				dialog.dispose();
			}
		};
		SwingUtilities.invokeLater(joinDialogRunner);
	}

	public static void main(String[] args) {
		joinLocalGame();
	}
}
