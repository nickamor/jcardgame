package networking.commands;

import java.io.Serializable;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.getAllPlayers() method.
 * 
 * @author Nicholas Amor
 *
 */
public class GetAllPlayersCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 4035375983857946370L;
	private Collection<Player> allPlayers = null;

	@Override
	public void execute(GameEngine engine) {
		allPlayers = engine.getAllPlayers();
		
		super.execute(engine);
	}
	
	public Collection<Player> getAllPlayers() {
		return allPlayers;
	}
}
