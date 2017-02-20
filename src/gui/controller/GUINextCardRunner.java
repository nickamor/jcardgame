package gui.controller;

import gui.view.GameFrame;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * Updates the view on the nextCard callback.
 * 
 * @author Nicholas Amor
 *
 */
class GUINextCardRunner implements Runnable {
	private final GameFrame frame;
	private final Player player;
	private final PlayingCard card;

	public GUINextCardRunner(GameFrame frame, Player player, PlayingCard card) {
		this.frame = frame;
		this.player = player;
		this.card = card;
	}

	@Override
	public void run() {
		if (frame.getPlayer().equals(player)) {
			frame.getGameView().nextCard(card);

			frame.getGameControl().log("%s was dealt a %s of %s", player.getPlayerName(), card.getValue(),
					card.getSuit());
		}
	}
}