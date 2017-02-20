package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import networking.GameEngineServerStub;

/**
 * Starts the server.
 * 
 * @author Nicholas Amor
 *
 */
public class ServerRunner {
	public static void main(String[] args) throws IOException {
		try {
			new GameEngineServerStub().run();
		} catch (IOException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "Unable to start server: " + e.getMessage(), e);
		}
	}
}
