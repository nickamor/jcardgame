package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.view.GameFrameRunner;

/**
 * Start a new game window to a network game.
 * 
 * @author Nicholas Amor
 *
 */
public class JoinGameAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		GameFrameRunner.joinLocalGame();
	}

}
