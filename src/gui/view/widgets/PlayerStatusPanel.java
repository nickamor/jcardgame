package gui.view.widgets;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.view.GameFrame;
import model.interfaces.Player;

/**
 * Shows detail view of {@link Player}.
 * 
 * @author Nicholas Amor
 *
 */
public class PlayerStatusPanel extends JPanel {
	private static final long serialVersionUID = -5731526219999501544L;
	private final JLabel name = new JLabel();
	private final JLabel score = new JLabel();
	private final JLabel bet = new JLabel();

	public void setPlayer(Player player) {
		name.setText(player.getPlayerName());
		score.setText("Score: " + Integer.toString(player.getPoints()));
		if (player.getBet() > 0) {
			bet.setText("Bet: " + Integer.toString(player.getBet()));
		} else {
			bet.setText("");
		}

		invalidate();
	}

	public PlayerStatusPanel(GameFrame frame) {
		super(new GridLayout(3, 1));

		name.setHorizontalAlignment(JLabel.CENTER);
		score.setHorizontalAlignment(JLabel.CENTER);
		bet.setHorizontalAlignment(JLabel.CENTER);

		Font font = score.getFont();
		Font boldFont = new Font(font.getName(), Font.BOLD, font.getSize());
		score.setFont(boldFont);
		bet.setFont(boldFont);

		add(name);
		add(score);
		add(bet);
	}
}
