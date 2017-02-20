package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import networking.events.GameEngineEvent;

/**
 * Listen for callback events over the network from the
 * {@link GameEngineServerStub} {@link ServerStubGameEngineCallback} and forward
 * the results to the original {@link GameEngineCallback}s of the client.
 * 
 * @author Nicholas Amor
 *
 */
public class ClientGameEngineCallbackServer implements Runnable {
	private final ServerSocket serverSocket;
	private final GameEngine gameEngine;

	private final Collection<GameEngineCallback> callbacks = new ConcurrentLinkedQueue<>();

	/**
	 * Creates a new {@link ClientGameEngineCallbackServer}.
	 * 
	 * @param gameEngine
	 *            The engine reference to pass to ensuing callbacks.
	 * @param port
	 * @throws IOException
	 */
	public ClientGameEngineCallbackServer(GameEngine gameEngine) throws IOException {
		this.gameEngine = gameEngine;
		serverSocket = new ServerSocket(0);
	}

	/**
	 * Return the port that this server is listening on.
	 * 
	 * @return
	 */
	public int getLocalPort() {
		return serverSocket.getLocalPort();
	}

	/**
	 * Return the local address of the server.
	 * 
	 * @return
	 */
	public InetAddress getInetAddress() {
		return serverSocket.getInetAddress();
	}

	/**
	 * Add a callback to the collection of callbacks to execute when we receive
	 * a {@link GameEngineEvent}.
	 * 
	 * @param callback
	 */
	public void addGameEngineCallback(GameEngineCallback callback) {
		callbacks.add(callback);
	}

	/**
	 * Listen for a client and read events from them. Since we can expect at
	 * most one client at a time, the server is not threaded.
	 */
	@Override
	public void run() {
		try {
			// Listen for client.
			Socket client = serverSocket.accept();
			ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());

			while (true) {
				// Listen for events.
				try {
					GameEngineEvent event = (GameEngineEvent) inputStream.readObject();

					for (GameEngineCallback callback : callbacks) {
						event.enact(callback, gameEngine);
					}
				} catch (ClassNotFoundException e) {
					// Unknown or invalid event; ignore.
					Logger.getAnonymousLogger().log(Level.FINE, "Unexpected command.", e);
				} catch (ClassCastException e) {
					// Unknown or invalid event; ignore.
					Logger.getAnonymousLogger().log(Level.FINE, "Unexpected command.", e);
				}
			}
		} catch (IOException e) {
			// Client disconnected or other socket issue; disconnect.
			Logger.getAnonymousLogger().log(Level.INFO, "Error on server socket - exiting...", e);
		}
	}
}
