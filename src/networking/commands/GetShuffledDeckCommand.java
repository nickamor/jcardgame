package networking.commands;

import java.io.Serializable;
import java.util.Deque;

import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;

/**
 * A Command representing the GameEngine.getShuffledDeck() method.
 * 
 * @author Nicholas Amor
 *
 */
public class GetShuffledDeckCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 7376109170667578340L;
	private Deque<PlayingCard> shuffledDeck = null;

	@Override
	public void execute(GameEngine engine) {
		shuffledDeck = engine.getShuffledDeck();
		
		super.execute(engine);
	}

	public Deque<PlayingCard> getShuffledDeck() {
		return shuffledDeck;
	}
}
