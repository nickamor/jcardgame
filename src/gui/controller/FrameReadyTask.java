package gui.controller;

import javax.swing.SwingWorker;

import gui.view.GameFrame;
import model.GameEngineCallbackImpl;
import model.interfaces.GameEngine;

/**
 * Add player and callbacks to game.
 * 
 * @author Nicholas Amor
 *
 */
public class FrameReadyTask extends SwingWorker<Void, Void> {
	private final GameFrame frame;

	public FrameReadyTask(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	protected Void doInBackground() throws Exception {
		GameEngine engine = frame.getGameEngine();

		engine.addPlayer(frame.getPlayer());

		engine.addGameEngineCallback(new GUIGameEngineCallback(frame));
		engine.addGameEngineCallback(new GameEngineCallbackImpl());

		return null;
	}

	@Override
	protected void done() {
		new RefreshViewTask(frame).execute();
	}

}
