package gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.view.GameFrame;
import model.interfaces.Player;

/**
 * Prompt the player for a bet, then try to place the bet on the GameEngine.
 * 
 * @author Nicholas Amor
 *
 */
public class BetAction implements ActionListener {
	private final GameFrame frame;
	private final JButton betButton;

	public BetAction(GameFrame frame, JButton betButton) {
		this.frame = frame;
		this.betButton = betButton;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Player player = frame.getPlayer();

		int bet = 0;

		// stop if the player has no points
		if (player.getPoints() <= 0) {
			JOptionPane.showMessageDialog(frame, "You've run out of points.");
			return;
		}
		
		// stop if the player has already bet
		if (player.getBet() > 0) {
			JOptionPane.showMessageDialog(frame, "You've already bet.");
			return;
		}

		// get bet from the user
		try {
			bet = Integer.parseInt(JOptionPane.showInputDialog("Place bet", player.getPoints() / 2));
		} catch (NumberFormatException except) {
			JOptionPane.showMessageDialog(frame, "That's not a valid number.");
			return;
		}

		// validate non-zero bet
		if (bet <= 0) {
			JOptionPane.showMessageDialog(frame, "Bet must be greater than zero.");
			return;
		}

		// try to place the bet
		if (player.placeBet(bet)) {
			betButton.setEnabled(false);

			new BetTask(frame).execute();
		} else {
			JOptionPane.showMessageDialog(frame, "You don't have that many points.");
			return;
		}
	}
}
