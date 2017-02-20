package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import networking.events.GameEngineEvent;

/**
 * Handles the blocking network activity of {@link ServerStubGameEngineCallback}
 * . Consumer of {@link GameEngineEvent}s.
 * 
 * @author Nicholas Amor
 *
 */
public class ServerStubGameEngineCallbackWorker implements Runnable {
	private final Socket socket;
	private final ObjectOutputStream output;
	private final BlockingQueue<GameEngineEvent> events = new LinkedBlockingQueue<>();

	public ServerStubGameEngineCallbackWorker(InetAddress inetAddress, int port) throws IOException {
		socket = new Socket(inetAddress, port);
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			while (true) {
				// Write out events as they come.
				GameEngineEvent event = events.take();

				output.writeObject(event);
			}
		} catch (InterruptedException | IOException e) {
			// Disconnect on error.

		}
	}

	public void addGameEngineEvent(GameEngineEvent event) {
		if (!Thread.interrupted()) {
			events.add(event);
		}
	}
}