package networking.commands;

import java.io.Serializable;

import model.interfaces.GameEngine;

/**
 * Abstract implementation of the {@link GameEngineCommand} interface. Provides
 * a memoization of the execution state of the command.
 * 
 * @author Nicholas Amor
 *
 */
public abstract class AbstractCommand implements GameEngineCommand, Serializable {
	private static final long serialVersionUID = -4513792768048180582L;
	private boolean executed = false;

	@Override
	public void execute(GameEngine engine) {
		executed = true;
	}
	
	public boolean done() {
		return executed;
	}
}
