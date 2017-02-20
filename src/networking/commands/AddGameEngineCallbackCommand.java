package networking.commands;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import networking.ClientGameEngineCallbackServer;
import networking.ServerStubGameEngineCallback;

/**
 * Encapsulates a GameEngine.addGameEngineCallback call. Creates a new
 * {@link ServerStubGameEngineCallback} connected to the Callback Server at the
 * given address and port.
 * 
 * @author Nicholas Amor
 *
 */
public class AddGameEngineCallbackCommand extends AbstractCommand implements Serializable {
	private static final long serialVersionUID = 3314880853507087158L;

	private final InetAddress inetAddress;
	private final int port;

	public AddGameEngineCallbackCommand(InetAddress inetAddress, int port) {
		this.inetAddress = inetAddress;
		this.port = port;
	}

	public AddGameEngineCallbackCommand(ClientGameEngineCallbackServer callbackServer) {
		this(callbackServer.getInetAddress(), callbackServer.getLocalPort());
	}

	@Override
	public void execute(GameEngine engine) {
		try {
			GameEngineCallback callback = new ServerStubGameEngineCallback(inetAddress, port);

			engine.addGameEngineCallback(callback);

			super.execute(engine);
		} catch (IOException e) {
			// Could not connect to callback server
			Logger.getAnonymousLogger().logp(Level.SEVERE, "ServerStubGameEngineCallback",
					"ServerStubGameEngineCallback()", e.getMessage(), e);
		}
	}
}
