package networking.events;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * A {@link GameEngineCallback}.result() call.
 * 
 * @author Nicholas Amor
 *
 */
public class ResultEvent implements GameEngineEvent, Serializable {
	private static final long serialVersionUID = 2738122416179247567L;

	private final Player player;
	private final int result;

	public ResultEvent(Player player, int result) {
		this.player = player;
		this.result = result;
	}

	@Override
	public void enact(GameEngineCallback callback, GameEngine engine) {
		callback.result(player, result, engine);
	}
}
