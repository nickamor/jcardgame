package gui.controller;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import gui.view.GameFrame;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Get players from the game and update the players list.
 * 
 * @author Nicholas Amor
 *
 */
public class RefreshViewTask extends SwingWorker<Void, Void> {
	private final GameFrame frame;
	private final GameEngine engine;
	private Player player;
	private Collection<Player> players = null;

	public RefreshViewTask(GameFrame frame) {
		this.frame = frame;
		this.engine = frame.getGameEngine();
		player = frame.getPlayer();
	}

	@Override
	protected Void doInBackground() throws Exception {
		player = engine.getPlayer(player.getPlayerId());
		players = engine.getAllPlayers();

		return null;
	}

	@Override
	protected void done() {
		try {
			get();
		} catch (InterruptedException e) {
			throw new RuntimeException("RefreshPlayersTask interrupted.", e);
		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(frame, e.getCause().getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		if (player != null) {
			frame.setPlayer(player);
			frame.getGameControl().getPlayerStatus().setPlayer(player);

		}

		if (players != null) {
			frame.getPlayersList().setPlayers(players);
		}
	}

}
