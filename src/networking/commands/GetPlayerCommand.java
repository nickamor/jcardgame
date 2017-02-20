package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.getPlayer(String) method.
 * 
 * @author Nicholas Amor
 *
 */
public class GetPlayerCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = -8401739536970730279L;
	private final String id;
	private Player player = null;

	public GetPlayerCommand(String id) {
		this.id = id;
	}

	@Override
	public void execute(GameEngine engine) {
		player = engine.getPlayer(id);
		
		super.execute(engine);
	}

	public Player getPlayer() {
		return player;
	}
}
