package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.view.GameFrame;

/**
 * Update the players list with players from the game.
 * 
 * @author Nicholas Amor
 *
 */
public class RefreshViewAction implements ActionListener {
	private final GameFrame frame;

	public RefreshViewAction(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new RefreshViewTask(frame).execute();
	}
}
