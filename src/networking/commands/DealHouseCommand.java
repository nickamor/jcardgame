package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;

/**
 * A Command representing the GameEngine.dealHouse(int) method.
 * 
 * @author Nicholas Amor
 *
 */
public class DealHouseCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 7379182714509480515L;

	private final int delay;

	public DealHouseCommand(int delay) {
		this.delay = delay;
	}

	@Override
	public void execute(GameEngine engine) {
		engine.dealHouse(delay);
		
		super.execute(engine);
	}
}
