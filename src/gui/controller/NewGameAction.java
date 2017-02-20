package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.view.GameFrameRunner;

/**
 * Start a new local game window.
 * 
 * @author Nicholas Amor
 *
 */
public class NewGameAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		GameFrameRunner.newGame();
	}
}