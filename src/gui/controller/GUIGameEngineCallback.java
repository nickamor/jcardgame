package gui.controller;

import javax.swing.SwingUtilities;

import gui.view.GameFrame;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * {@link GameEngineCallback} for graphical interface.
 * 
 * @author Nicholas Amor
 * @see GameFrame
 *
 */
public class GUIGameEngineCallback implements GameEngineCallback {
	private final GameFrame frame;

	public GUIGameEngineCallback(GameFrame frame) {
		this.frame = frame;
	}
	
	private void invoke(Runnable eventRunner) {
		SwingUtilities.invokeLater(eventRunner);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		invoke(new GUINextCardRunner(frame, player, card));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		invoke(new GUIBustCardRunner(frame, player, card));
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		invoke(new GUIResultRunner(frame, player, result));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		invoke(new GUINextHouseCardRunner(frame, card));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		invoke(new GUIHouseBustCardRunner(frame, card));
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		invoke(new GUIHouseResultRunner(frame, result));
	}
}
