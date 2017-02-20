package gui.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import gui.view.GameFrame;

/**
 * Present a confirm quit prompt.
 * 
 * @author Nicholas Amor
 *
 */
public class ConfirmQuitRunner implements Runnable {
	private final GameFrame frame;

	public ConfirmQuitRunner(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Quit Speed21",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			SwingWorker<Void, Void> task = new RemovePlayerAndQuitTask(frame);
			task.execute();
		}
	}
}
