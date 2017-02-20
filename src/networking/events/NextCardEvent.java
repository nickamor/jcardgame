package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * A {@link GameEngineCallback}.nextCard call. See also
 * {@link GameEngineEvent}
 * 
 * @author Nicholas Amor
 *
 */
public class NextCardEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = 4956193241692838024L;

	private final Player player;
	private final PlayingCard card;

	/**
	 * Creates a new {@link NextCardEvent}.
	 */
	public NextCardEvent(Player player, PlayingCard card) {
		this.player = player;
		this.card = card;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.nextCard(player, card, engine);
	}
}
