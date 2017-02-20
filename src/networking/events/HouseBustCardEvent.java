package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.PlayingCard;

/**
 * A {@link GameEngineCallback}.houseBustCard() call.
 * 
 * @author Nicholas Amor
 *
 */
public class HouseBustCardEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = -1180775173910879418L;

	private final PlayingCard card;
	public HouseBustCardEvent(PlayingCard card) {
		this.card = card;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.houseBustCard(card, engine);
	}
}
