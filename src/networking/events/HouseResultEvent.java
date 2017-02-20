package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

/**
 * A {@link GameEngineCallback}.houseResult() call.
 * 
 * @author Nicholas Amor
 *
 */
public class HouseResultEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = -4701366219172532333L;

	private final int result;
	public HouseResultEvent(int result) {
		this.result = result;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.houseResult(result, engine);
	}
}
