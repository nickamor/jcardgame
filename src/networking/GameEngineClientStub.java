package networking;

/**
 * TODO: This class has too high fan-out
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import networking.commands.AddGameEngineCallbackCommand;
import networking.commands.AddPlayerCommand;
import networking.commands.CalculateResultCommand;
import networking.commands.DealHouseCommand;
import networking.commands.DealPlayerCommand;
import networking.commands.GameEngineCommand;
import networking.commands.GetAllPlayersCommand;
import networking.commands.GetPlayerCommand;
import networking.commands.GetShuffledDeckCommand;
import networking.commands.PlaceBetCommand;
import networking.commands.RemovePlayerCommand;

/**
 * Client-side interface for the multiplayer game. Performs all client-side
 * networking functionality. Is expected to block on IO. Contains a
 * CallbackServer to listen for server callbacks.
 * 
 * @author Nicholas Amor
 * @see ClientGameEngineCallbackServer
 *
 */
public class GameEngineClientStub implements GameEngine {
	private final Socket socket;
	private final ObjectOutputStream output;
	private final ObjectInputStream input;
	private final ClientGameEngineCallbackServer callbackServer;
	private final Thread callbackServerThread;

	/**
	 * Create a new {@link GameEngineClientStub} connection to the default
	 * {@link GameEngineServerStub}.
	 * 
	 * @throws IOException
	 */
	public GameEngineClientStub() throws IOException {
		this("localhost", GameEngineServerStub.DEFAULT_PORT);
	}

	/**
	 * Create a new {@link GameEngineClientStub} connection to a given
	 * {@link GameEngineServerStub}.
	 * 
	 * @param host
	 * @param port
	 * 
	 * @throws IOException
	 *             If there was some problem with the socket.
	 */
	public GameEngineClientStub(String host, int port) throws IOException {
		socket = new Socket(host, port);

		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());

		callbackServer = new ClientGameEngineCallbackServer(this);
		
		callbackServerThread = new Thread(callbackServer);
		callbackServerThread.setDaemon(true);
		callbackServerThread.start();
	}

	/**
	 * Send a command to the server to be executed, and receive a response from
	 * the server.
	 * 
	 * @param request
	 *            The command to be executed on the server.
	 * @return
	 */
	private GameEngineCommand execute(GameEngineCommand request) {
		GameEngineCommand response = null;
		try {
			output.writeObject(request);

			response = (GameEngineCommand) input.readObject();

			/**
			 * If the command was not executed, something went wrong.
			 */
			if (!response.done()) {
				throw new RuntimeException("Command was not executed.");
			}

			return response;
		} catch (IOException e) {
			// Server socket return error; the ClientStub cannot continue in
			// this state.
			callbackServerThread.interrupt();
			throw new RuntimeException("Lost connection to server.", e);
		} catch (ClassCastException e) {
			// Received message from server that was not GameEngineCommand as
			// expected; ignore with warning
			Logger.getAnonymousLogger().logp(Level.WARNING, "GameEngineClientStub", "execute",
					"Received Object not a Command.", e);
		} catch (ClassNotFoundException e) {
			// Received unexpected message from server; ignore with warning
			Logger.getAnonymousLogger().logp(Level.WARNING, "GameEngineClientStub", "execute",
					"Received unknown object.", e);
		}

		return response;
	}

	/**
	 * Deal cards to this player.
	 */
	@Override
	public void dealPlayer(Player player, int delay) {
		execute(new DealPlayerCommand(player, delay));
	}

	/**
	 * Deal cards to the house.
	 */
	@Override
	public void dealHouse(int delay) {
		execute(new DealHouseCommand(delay));
	}

	/**
	 * Add a player to the game.
	 */
	@Override
	public void addPlayer(Player player) {
		execute(new AddPlayerCommand(player));
	}

	/**
	 * Get the player with the given id, or null if there's no such player.
	 */
	@Override
	public Player getPlayer(String id) {
		GetPlayerCommand request = new GetPlayerCommand(id);

		GetPlayerCommand response = (GetPlayerCommand) execute(request);

		return response.getPlayer();
	}

	/**
	 * Remove this player from the game.
	 */
	@Override
	public boolean removePlayer(Player player) {
		RemovePlayerCommand request = new RemovePlayerCommand(player);

		RemovePlayerCommand response = (RemovePlayerCommand) execute(request);

		return response.getPlayerRemoved();
	}

	/**
	 * Calculate the winners and losers of the game.
	 */
	@Override
	public void calculateResult() {
		execute(new CalculateResultCommand());
	}

	/**
	 * Add a callback class to respond to events with. Will only send a remote
	 * command the first time.
	 */
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		execute(new AddGameEngineCallbackCommand(callbackServer));

		callbackServer.addGameEngineCallback(gameEngineCallback);
	}

	/**
	 * Get all the players currently in the game.
	 */
	@Override
	public Collection<Player> getAllPlayers() {
		GetAllPlayersCommand request = new GetAllPlayersCommand();

		GetAllPlayersCommand response = (GetAllPlayersCommand) execute(request);

		return response.getAllPlayers();
	}

	/**
	 * Attempt to place a given bet with a given player.
	 */
	@Override
	public boolean placeBet(Player player, int bet) {
		PlaceBetCommand request = new PlaceBetCommand(player, bet);

		PlaceBetCommand response = (PlaceBetCommand) execute(request);

		return response.getBetPlaced();
	}

	/**
	 * Get a 52 card deck of {@link PlayingCard}s in random order.
	 */
	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		GetShuffledDeckCommand request = new GetShuffledDeckCommand();

		GetShuffledDeckCommand response = (GetShuffledDeckCommand) execute(request);

		return response.getShuffledDeck();
	}
}
