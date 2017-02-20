package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * A {@link GameEngineCallback}.bustCard() method call.
 * 
 * @author Nicholas Amor
 *
 */
public class BustCardEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = 8599683420757114575L;

	private final Player player;
	private final PlayingCard card;

	public BustCardEvent(Player player, PlayingCard card) {
		this.player = player;
		this.card = card;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.bustCard(player, card, engine);
	}
}
