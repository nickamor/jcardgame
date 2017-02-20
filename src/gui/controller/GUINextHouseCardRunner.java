package gui.controller;

import gui.view.GameFrame;
import model.interfaces.PlayingCard;

/**
 * Updates the view on the nextHouseCard.
 * 
 * @author Nicholas Amor
 *
 */
class GUINextHouseCardRunner implements Runnable {
	private final GameFrame frame;
	private final PlayingCard card;

	public GUINextHouseCardRunner(GameFrame frame, PlayingCard card) {
		this.frame = frame;
		this.card = card;
	}

	@Override
	public void run() {
		frame.getGameControl().log("House was dealt a %s of %s", card.getValue(), card.getSuit());

		frame.getGameView().houseCard(card);
	}
}