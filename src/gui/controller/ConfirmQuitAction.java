package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.view.GameFrame;

/**
 * Present a confirm quit prompt.
 * 
 * @author Nicholas Amor
 *
 */
public class ConfirmQuitAction implements ActionListener {
	private final GameFrame frame;

	public ConfirmQuitAction(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ConfirmQuitRunner(frame).run();
	}
}
