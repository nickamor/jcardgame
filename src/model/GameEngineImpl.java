package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * Implementation of the {@link GameEngine} interface.
 * 
 * @author Nicholas Amor
 *
 */
public class GameEngineImpl implements GameEngine {

	/**
	 * Abstraction of card dealing logic.
	 * 
	 * @author Nicholas Amor
	 *
	 */
	abstract class DealTask {
		abstract void onNextCard(PlayingCard card);

		abstract void onBustCard(PlayingCard card);

		abstract void onResult(int result);

		void deal(int delay) {

			Deque<PlayingCard> deck = getShuffledDeck();

			int score = 0;

			while (true) {
				// wait to deal card
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// Early wake-up; ignore.
				}

				// deal card
				PlayingCard card = deck.pop();

				// call nextCard or bustCard
				if (score + card.getScore() < BUST_LEVEL) {
					onNextCard(card);

					score += card.getScore();
				} else {
					onBustCard(card);

					break;
				}
			}

			// set result
			onResult(score);
		}
	}

	class DealPlayerTask extends DealTask {
		private final Player player;

		public DealPlayerTask(Player player) {
			this.player = player;
		}

		@Override
		void onNextCard(PlayingCard card) {
			for (GameEngineCallback callback : callbacks) {
				callback.nextCard(player, card, GameEngineImpl.this);
			}
		}

		@Override
		void onBustCard(PlayingCard card) {
			for (GameEngineCallback callback : callbacks) {
				callback.bustCard(player, card, GameEngineImpl.this);
			}
		}

		@Override
		void onResult(int result) {
			for (GameEngineCallback callback : callbacks) {
				callback.result(player, result, GameEngineImpl.this);
			}
			player.setResult(result);
		}
	}

	class DealHouseTask extends DealTask {

		@Override
		void onNextCard(PlayingCard card) {
			for (GameEngineCallback callback : callbacks) {
				callback.nextHouseCard(card, GameEngineImpl.this);
			}
		}

		@Override
		void onBustCard(PlayingCard card) {
			for (GameEngineCallback callback : callbacks) {
				callback.houseBustCard(card, GameEngineImpl.this);
			}
		}

		@Override
		void onResult(int result) {
			for (GameEngineCallback callback : callbacks) {
				callback.houseResult(result, GameEngineImpl.this);
			}
			lastResult = result;
		}
	}

	private static final int DEFAULT_DELAY = 200;
	private final Map<String, Player> players = new ConcurrentHashMap<>();
	private final Collection<GameEngineCallback> callbacks = new ConcurrentLinkedQueue<>();
	private final List<Player> bettingPlayers = new Vector<>();
	private int lastResult = 0;

	private void validateDelay(int delay) {
		if (delay < 0) {
			throw new IllegalArgumentException("delay must be greater than zero.");
		}
	}

	@Override
	public void dealPlayer(Player playerParam, int delay) {
		final Player player = getPlayer(playerParam.getPlayerId());

		/** validation **/
		validateDelay(delay);
		if (player == null) {
			throw new IllegalArgumentException("Can not deal for this unknown player.");
		}
		if (player.getBet() <= 0) {
			throw new IllegalArgumentException("Can not deal for a player who hasn't bet.");
		}

		new DealPlayerTask(player).deal(delay);
	}

	@Override
	public void dealHouse(int delay) {
		validateDelay(delay);

		new DealHouseTask().deal(delay);
	}

	/**
	 * Adds a player to the game. Updates a player of the same ID.
	 */
	@Override
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		return players.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player.getPlayerId()) != null;
	}

	@Override
	public void calculateResult() {
		dealHouse(DEFAULT_DELAY);

		for (Player player : bettingPlayers) {
			// Only calculate for players that have bet.
			if (player.getBet() <= 0) {
				continue;
			}

			dealPlayer(player, DEFAULT_DELAY);

			if (player.getResult() > lastResult) {
				// Player wins!
				player.setPoints(player.getPoints() + (player.getBet() * 2));
			} else if (player.getResult() == lastResult) {
				// Player and House draw.
				player.setPoints(player.getPoints() + player.getBet());
			} else {
				// Player loses...
				// Set points so that the bet is reset
				player.setPoints(player.getPoints());
			}
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callbacks.add(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return new ArrayList<>(players.values());
	}

	/**
	 * Returns true if and only if the given player is in the game, and that
	 * player can place the given bet.
	 * 
	 * @see GameEngine
	 */
	@Override
	public boolean placeBet(Player player, int bet) {
		Player p = players.get(player.getPlayerId());

		if (p == null) {
			throw new IllegalArgumentException("Can not, will not, accept a bet from an unknown player.");
		} else if (p.placeBet(bet)) {
			bettingPlayers.add(p);
			return true;
		}

		return false;
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		LinkedList<PlayingCard> deck = new LinkedList<PlayingCard>();

		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				deck.add(new PlayingCardImpl(suit, value));
			}
		}

		Collections.shuffle(deck);

		return deck;
	}
}
