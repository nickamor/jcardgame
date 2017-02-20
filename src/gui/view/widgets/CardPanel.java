package gui.view.widgets;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.view.CardResources;
import gui.view.GameFrame;
import model.interfaces.PlayingCard;

/**
 * A panel that displays a single PlayingCard.
 * 
 * @author Nicholas Amor
 *
 */
public class CardPanel extends JPanel {

	private static final long serialVersionUID = 3054618392249691754L;

	private JLabel cardImage;
	private JLabel cardText;

	public CardPanel(PlayingCard card) {
		setLayout(new GridLayout(2, 0));

		cardImage = new JLabel(CardResources.getCardImageIcon(card));
		cardText = new JLabel(CardResources.getCardDescription(card));

		cardImage.setPreferredSize(CardResources.getPreferredSize());
		cardText.setMaximumSize(new Dimension(113, 24));
		cardText.setVerticalAlignment(JLabel.TOP);
		cardText.setHorizontalAlignment(JLabel.CENTER);

		// text style
		cardText.setForeground(GameFrame.foregroundColor());
		setOpaque(false);

		add(cardImage);
		add(cardText);

		repaint();
	}
}
