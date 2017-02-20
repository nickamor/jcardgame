package model;

import java.io.Serializable;

import model.interfaces.Player;

/**
 * Implementation of the {@link Player}. interface.
 * 
 * @author Nicholas Amor
 *
 */
public class SimplePlayer implements Player, Serializable {
	private static final long serialVersionUID = -4191372417710511373L;

	private String playerID;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	/**
	 * Create a new SimplePlayer with the given details.
	 * 
	 * @param playerID
	 * @param playerName
	 * @param points
	 */
	public SimplePlayer(String playerID, String playerName, int points) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.points = points;
		bet = 0;
		result = 0;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
		this.bet = 0;
	}

	@Override
	public String getPlayerId() {
		return playerID;
	}

	/**
	 * Set bet if player has enough score. Reset bet if replacing it.
	 */
	@Override
	public boolean placeBet(int bet) {
		if (bet == 0) {
			// reset bet
			this.bet = 0;
		} else if (bet > 0 && bet <= points && this.bet <= 0) {
			// place bet
			this.bet = bet;
			this.points -= bet;

			return true;
		}

		return false;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return String.format("%s (id: %s, points: %d, bet: %d)", playerName, playerID, points, bet);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;

			return playerID.equals(player.getPlayerId()) && playerName.equals(player.getPlayerName());
		}
		// Warning! Score, bet and result aren't compared.

		return false;
	}
}
