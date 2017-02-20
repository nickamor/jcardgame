package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.addPlayer(Player) method.
 * 
 * @author Nicholas Amor
 *
 */
public class AddPlayerCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 8448074908232497367L;

	private final Player player;

	public AddPlayerCommand(Player player) {
		this.player = player;
	}

	@Override
	public void execute(GameEngine engine) {
		engine.addPlayer(player);
		
		super.execute(engine);
	}

	/**
	 * Get the Player this Command wants to add.
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
}
