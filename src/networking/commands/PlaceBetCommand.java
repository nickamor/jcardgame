package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A Command representing the GameEngine.placeBet(Player, int) method.
 * 
 * @author Nicholas Amor
 *
 */
public class PlaceBetCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 8325288937191022770L;
	private final Player player;
	private final int bet;
	private boolean placeBet;

	public PlaceBetCommand(Player player, int bet) {
		this.player = player;
		this.bet = bet;
	}

	@Override
	public void execute(GameEngine engine) {
		placeBet = engine.placeBet(player, bet);
		
		super.execute(engine);
	}

	public boolean getBetPlaced() {
		return placeBet;
	}
}
