package gui.controller;

import gui.view.GameFrame;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * Updates the view on the bustCard callback.
 * 
 * @author Nicholas Amor
 *
 */
class GUIBustCardRunner implements Runnable {
	private final GameFrame frame;
	private final Player player;
	private final PlayingCard card;

	public GUIBustCardRunner(GameFrame frame, Player player, PlayingCard card) {
		this.frame = frame;
		this.player = player;
		this.card = card;
	}

	@Override
	public void run() {
		if (frame.getPlayer().equals(player)) {
			frame.getGameView().bustCard(card);

			frame.getGameControl().log("%s was dealt a %s of %s and went bust", player.getPlayerName(), card.getValue(),
					card.getSuit());
		}
	}
}