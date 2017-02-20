package gui.controller;

import gui.view.GameFrame;
import model.interfaces.Player;

/**
 * Updates the view on the result callback.
 * 
 * @author Nicholas Amor
 *
 */
class GUIResultRunner implements Runnable {
	private final GameFrame frame;
	private final Player player;
	private final int result;

	public GUIResultRunner(GameFrame frame, Player player, int result) {
		this.frame = frame;
		this.player = player;
		this.result = result;
	}

	@Override
	public void run() {
		if (frame.getPlayer().equals(player)) {
			frame.getGameView().result(result);

			frame.getGameControl().log("%s scored %d", player.getPlayerName(), result);
		}
	}
}