package gui.controller;

import javax.swing.SwingWorker;

import gui.view.GameFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Try to place bet on the current player.
 * 
 * @author Nicholas Amor
 *
 */
public class BetTask extends SwingWorker<Void, Void> {
	private final GameFrame frame;
	private final GameEngine engine;
	private final Player player;

	public BetTask(GameFrame frame) {
		this.frame = frame;
		engine = frame.getGameEngine();
		player = frame.getPlayer();
	}

	@Override
	protected Void doInBackground() throws Exception {
		engine.placeBet(player, player.getBet());

		return null;
	}

	@Override
	protected void done() {
		frame.getGameControl().getBetButton().setEnabled(true);
		new RefreshViewTask(frame).execute();
	}
}
