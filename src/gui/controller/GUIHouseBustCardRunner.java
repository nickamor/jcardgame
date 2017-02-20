package gui.controller;

import gui.view.GameFrame;
import model.interfaces.PlayingCard;

/**
 * Updates the view on houseBustCard.
 * 
 * @author Nicholas Amor
 *
 */
class GUIHouseBustCardRunner implements Runnable {
	private final GameFrame frame;
	private final PlayingCard card;

	public GUIHouseBustCardRunner(GameFrame frame, PlayingCard card) {
		this.frame = frame;
		this.card = card;
	}

	@Override
	public void run() {
		frame.getGameControl().log("House was dealt a %s of %s and went bust", card.getValue(), card.getSuit());

		frame.getGameView().houseBustCard(card);
	}
}