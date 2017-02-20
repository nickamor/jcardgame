package networking.events;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

/**
 * Encapsulates a {@link GameEngineCallback} method call.
 * 
 * @author Nicholas Amor
 *
 */
public interface GameEngineEvent {
	/**
	 * Performs the appropriate callback method on this callback.
	 * 
	 * @param callback
	 * @param engine
	 */
	public void enact(GameEngineCallback callback, GameEngine engine);
}
