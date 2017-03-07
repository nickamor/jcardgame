package model.interfaces;

import java.util.Collection;
import java.util.Deque;

public interface GameEngine {

	static final int BUST_LEVEL = 21;

	void dealPlayer(Player player, int delay);

	void dealHouse(int delay);

	void addPlayer(Player player);

	Player getPlayer(String id);

	boolean removePlayer(Player player);

	void calculateResult();

	void addGameEngineCallback(GameEngineCallback gameEngineCallback);

	Collection<Player> getAllPlayers();

	boolean placeBet(Player player, int bet);

	Deque<PlayingCard> getShuffledDeck();

}
