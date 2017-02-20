package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.dealPlayer(Player, int) method.
 * 
 * @author Nicholas Amor
 *
 */
public class DealPlayerCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = -5716127082671634159L;

	private final Player player;
	private final int delay;

	public DealPlayerCommand(Player player, int delay) {
		this.player = player;
		this.delay = delay;
	}

	@Override
	public void execute(GameEngine engine) {
		engine.dealPlayer(player, delay);
		
		super.execute(engine);
	}
}
