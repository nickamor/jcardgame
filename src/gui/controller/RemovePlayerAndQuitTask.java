package gui.controller;

import javax.swing.SwingWorker;

import gui.view.GameFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Remove the GameFrame's player from the GameEngine in preparation of quiting.
 * 
 * @author Nicholas Amor
 *
 */
public class RemovePlayerAndQuitTask extends SwingWorker<Void, Void> {
	private final GameFrame frame;
	private final Player player;
	private final GameEngine engine;

	public RemovePlayerAndQuitTask(GameFrame frame) {
		this.frame = frame;
		this.engine = frame.getGameEngine();
		this.player = frame.getPlayer();
	}

	@Override
	protected Void doInBackground() throws Exception {
		engine.removePlayer(player);
		return null;
	}

	@Override
	protected void done() {
		frame.setVisible(false);
		frame.dispose();
	}
}
