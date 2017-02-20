package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

/**
 * Server-side networking functionality. Utilises threads for client IO and
 * engine commands.
 * 
 * @author Nicholas Amor
 * @see GameEngineServerStubThread
 * @see GameEngineServerStubCommandThread
 *
 */
public class GameEngineServerStub implements Runnable {
	/**
	 * The port the server listens on, and that clients should connect to.
	 */
	public static final int DEFAULT_PORT = 11235;
	private final ServerSocket serverSocket;
	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	private final GameEngine engine = new GameEngineImpl();

	/**
	 * Creates a new ServerStub.
	 * 
	 * @throws IOException
	 */
	public GameEngineServerStub() throws IOException {
		try {
			serverSocket = new ServerSocket(DEFAULT_PORT);
		} catch (IOException e) {
			// Cannot continue without a server socket. Wrap exception with more
			// instructive message.
			throw new IOException("Could not create server socket.", e);
		}
	}

	@Override
	public void run() {
		// accept clients
		try {
			while (true) {
				final Socket socket = serverSocket.accept();

				Thread thread = new GameEngineServerStubThread(engine, socket);
				thread.setDaemon(true);
				thread.start();
			}
		} catch (IOException e) {
			logger.log(Level.WARNING, String.format("Server socket error: %s", e.getMessage()));
		}
	}
}
