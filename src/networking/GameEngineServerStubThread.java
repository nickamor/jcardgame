package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import networking.commands.GameEngineCommand;

/**
 * Handles client activity. Child thread of {@link GameEngineServerStub}.
 * Producer to {@link GameEngineServerStubCommandThread}.
 * 
 * @author Nicholas Amor
 */
public class GameEngineServerStubThread extends Thread {
	private static final String CLASS_NAME = GameEngineServerStubThread.class.getSimpleName();
	private final Logger logger = Logger.getLogger(CLASS_NAME);
	private final Socket socket;
	private final ObjectInputStream input;
	private final ObjectOutputStream output;
	private final GameEngine engine;

	private void log(String sourceMethod, String format, Object... args) {
		logger.logp(Level.INFO, CLASS_NAME, sourceMethod, String.format(format, args));
	}

	private void logW(String sourceMethod, String format, Throwable thrown, Object... args) {
		logger.logp(Level.WARNING, CLASS_NAME, sourceMethod, String.format(format, args), thrown);
	}

	/**
	 * Creates a new {@link GameEngineServerStubThread}.
	 * 
	 * @param server
	 *            The parent.
	 * 
	 * @param socket
	 *            Client socket for accepting Commands.
	 * @throws IOException
	 */
	public GameEngineServerStubThread(GameEngine engine, Socket socket) throws IOException {
		this.engine = engine;
		this.socket = socket;

		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());

		log("GameEngineServerStubThread", "New client from %s on port %d.", socket.getInetAddress().getHostName(),
				socket.getPort());
	}

	/**
	 * Accepts {@link GameEngineCommand}s and returns responses to and from the
	 * client socket.
	 * 
	 * @throws IOException
	 */
	private void handleClient() throws IOException {
		try {
			// Accept command from client.
			GameEngineCommand command = (GameEngineCommand) input.readObject();

			// Perform the client's command.
			synchronized (engine) {
				command.execute(engine);
			}

			// Return response to client.
			output.writeObject(command);
			output.reset();
		} catch (ClassNotFoundException e) {
			// Unknown or invalid command; ignore.
			logW("handleClient", "Unknown command received from client on port %d.", e, socket.getPort());
		}
	}

	/**
	 * Loop while the client connection is still available.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				handleClient();
			} catch (IOException e) {
				// Some error with the client socket; disconnect. It's on the
				// client to reconnect so there's nothing to do here.
				break;
			}
		}

		log("run", "Client on port %d disconnected.", socket.getPort());
	}
}