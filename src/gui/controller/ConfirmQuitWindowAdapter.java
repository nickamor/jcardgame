package gui.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.view.GameFrame;

/**
 * Present a confirm quit prompt.
 * 
 * @author Nicholas Amor
 *
 */
public class ConfirmQuitWindowAdapter extends WindowAdapter {
	private final GameFrame frame;

	public ConfirmQuitWindowAdapter(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		new ConfirmQuitRunner(frame).run();
	}
}