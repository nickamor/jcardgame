package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * A simple {@link GameEngineCallback} that logs each event.
 * 
 * @author Nicholas Amor
 *
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private void log(String sourceMethod, String format, Object... args) {
		System.out.println(String.format(format, args));
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		log("nextCard", "Player %s was dealt %s.", player.getPlayerName(), card);
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		log("bustCard", "Player %s was dealt %s and went bust.", player.getPlayerName(), card);
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		log("result", "Player %s scored %d.", player.getPlayerName(), result);
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		log("nextHouseCard", "The House was dealt %s", card);
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		log("houseBustCard", "The House was dealt %s and went bust.", card);
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		log("houseResult", "The House scored %d.", result);
	}
}
