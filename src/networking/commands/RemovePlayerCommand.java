package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.removePlayer(Player) method.
 * 
 * @author Nicholas Amor
 *
 */
public class RemovePlayerCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 4568010351721188778L;

	private final Player player;

	private boolean removePlayer = false;

	public RemovePlayerCommand(Player player) {
		this.player = player;
	}

	@Override
	public void execute(GameEngine engine) {
		removePlayer = engine.removePlayer(player);
		
		super.execute(engine);
	}

	public boolean getPlayerRemoved() {
		return removePlayer;
	}
}
