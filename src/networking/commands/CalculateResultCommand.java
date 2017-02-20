package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;

/**
 * A Command representing the GameEngine.calculateResult() method.
 * 
 * @author Nicholas Amor
 *
 */
public class CalculateResultCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 1205510182735862500L;

	@Override
	public void execute(GameEngine engine) {
		engine.calculateResult();
		
		super.execute(engine);
	}
}
