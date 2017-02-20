package networking;

import java.io.IOException;
import java.net.InetAddress;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import networking.events.BustCardEvent;
import networking.events.GameEngineEvent;
import networking.events.HouseBustCardEvent;
import networking.events.HouseResultEvent;
import networking.events.NextCardEvent;
import networking.events.NextHouseCardEvent;
import networking.events.ResultEvent;

/**
 * Client to the ClientGameEngineCallbackServer. Sends {@link GameEngineEvent}s
 * to the server as {@link GameEngineCallback} methods are called.
 * 
 * @author Nicholas Amor
 *
 */
public class ServerStubGameEngineCallback implements GameEngineCallback {
	private final ServerStubGameEngineCallbackWorker worker;

	/**
	 * Create a new callback connected to the callback server.
	 * 
	 * @param inetAddress
	 * @param port
	 * @throws IOException
	 */
	public ServerStubGameEngineCallback(InetAddress inetAddress, int port) throws IOException {
		try {
			worker = new ServerStubGameEngineCallbackWorker(inetAddress, port);
			Thread thread = new Thread(worker);

			thread.setDaemon(true);
			thread.start();
		} catch (IOException e) {
			throw new IOException("Could not connect to CallbackServer", e);
		}
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		worker.addGameEngineEvent(new NextCardEvent(player, card));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		worker.addGameEngineEvent(new BustCardEvent(player, card));
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		worker.addGameEngineEvent(new ResultEvent(player, result));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		worker.addGameEngineEvent(new NextHouseCardEvent(card));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		worker.addGameEngineEvent(new HouseBustCardEvent(card));
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		worker.addGameEngineEvent(new HouseResultEvent(result));
	}
}
