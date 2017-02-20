package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gui.view.GameFrame;

/**
 * Deal player and house cards, and calculate result.
 * 
 * @author Nicholas Amor
 *
 */
public class DealAction implements ActionListener {
	private final GameFrame frame;
	private final JButton button;

	public DealAction(GameFrame frame, JButton button) {
		this.frame = frame;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		button.setEnabled(false);

		new DealTask(frame).execute();
	}
}
