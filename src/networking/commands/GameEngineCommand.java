package networking.commands;

import model.interfaces.GameEngine;

/**
 * Interface for a command to be executed on a remote GameEngine. Based on the
 * Command design pattern.
 * 
 * @author Nicholas Amor
 *
 */
public interface GameEngineCommand {
	/**
	 * Execute the command, and return the result to be sent to the client.
	 * 
	 * @param engine
	 *            The GameEngine to execute the command on.
	 */
	public void execute(GameEngine engine);

	/**
	 * Whether the command has been successfully executed by the server.
	 */
	public boolean done();
}