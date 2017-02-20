package gui.controller;

import javax.swing.SwingWorker;

import gui.view.GameFrame;
import model.interfaces.GameEngine;

/**
 * Deal player and house, and calculate result.
 * 
 * @author Nicholas Amor
 *
 */
public class DealTask extends SwingWorker<Void, Void> {
	private final GameFrame frame;
	private final GameEngine engine;

	public DealTask(GameFrame frame) {
		this.frame = frame;
		engine = frame.getGameEngine();
	}

	@Override
	protected Void doInBackground() throws Exception {
		engine.calculateResult();

		return null;
	}

	@Override
	protected void done() {
		frame.getGameControl().getDealButton().setEnabled(true);
		new RefreshViewTask(frame).execute();
	}

}
