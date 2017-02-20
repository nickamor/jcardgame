package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.PlayingCard;

/**
 * A {@link GameEngineCallback}.nextHouseCard call.
 * 
 * @author Nicholas Amor
 *
 */
public class NextHouseCardEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = -6879124106082234162L;

	private final PlayingCard card;

	public NextHouseCardEvent(PlayingCard card) {
		this.card = card;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.nextHouseCard(card, engine);
	}
}
