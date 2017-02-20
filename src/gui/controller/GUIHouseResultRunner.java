package gui.controller;

import gui.view.GameFrame;

/**
 * Updates the view on houseResult.
 * 
 * @author Nicholas Amor
 *
 */
class GUIHouseResultRunner implements Runnable {
	private final GameFrame frame;
	private final int result;

	public GUIHouseResultRunner(GameFrame frame, int result) {
		this.frame = frame;
		this.result = result;
	}

	@Override
	public void run() {
		frame.getGameControl().log("House scored %d", result);

		frame.getGameView().houseResult(result);
	}
}