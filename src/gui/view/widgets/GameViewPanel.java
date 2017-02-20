package gui.view.widgets;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.view.GameFrame;
import model.interfaces.PlayingCard;

/**
 * The view of the game: house and player cards, and their results.
 * 
 * @see CardsPanel
 * @author Nicholas Amor
 *
 */
public class GameViewPanel extends JPanel {

	private static final long serialVersionUID = 2602902067327960818L;
	private final CardsPanel houseCards = new CardsPanel();
	private final CardsPanel playerCards = new CardsPanel();
	private final JLabel houseResult = new JLabel();
	private final JLabel playerResult = new JLabel();

	private boolean dirty;

	public GameViewPanel() {
		setLayout(new GridLayout(2, 0));
		setOpaque(false);

		houseResult.setForeground(GameFrame.foregroundColor());
		houseResult.setVerticalAlignment(JLabel.TOP);
		houseResult.setHorizontalAlignment(JLabel.CENTER);

		playerResult.setForeground(GameFrame.foregroundColor());
		playerResult.setVerticalAlignment(JLabel.TOP);
		playerResult.setHorizontalAlignment(JLabel.CENTER);

		JPanel houseView = new JPanel(new BorderLayout());
		houseView.setOpaque(false);
		houseView.add(houseCards, BorderLayout.CENTER);
		houseView.add(houseResult, BorderLayout.SOUTH);

		JPanel playerView = new JPanel(new BorderLayout());
		playerView.setOpaque(false);
		playerView.add(playerCards, BorderLayout.CENTER);
		playerView.add(playerResult, BorderLayout.SOUTH);

		add(houseView);
		add(playerView);
	}

	public CardsPanel getPlayerPanel() {
		return playerCards;
	}

	public CardsPanel getHousePanel() {
		return houseCards;
	}

	private void setResultText(JLabel label, int result) {
		label.setText(String.format("Result: %d", result));
	}

	public void clear() {
		playerCards.clear();
		playerResult.setText("");

		houseCards.clear();
		houseResult.setText("");

		dirty = false;
	}

	private void addCard(CardsPanel panel, PlayingCard card, boolean dirty) {
		if (this.dirty) {
			clear();
		}
		
		panel.addPlayingCard(card, dirty);
	}
	
	private void addCard(CardsPanel panel, PlayingCard card) {
		addCard(panel, card, false);
	}

	public void nextCard(PlayingCard card) {
		addCard(playerCards, card);
	}

	public void bustCard(PlayingCard card) {
		addCard(playerCards, card, true);
		
		dirty = true;
	}

	public void houseCard(PlayingCard card) {
		addCard(houseCards, card);
	}

	public void houseBustCard(PlayingCard card) {
		addCard(houseCards, card, true);
	}

	public void result(int result) {
		setResultText(playerResult, result);
	}

	public void houseResult(int result) {
		setResultText(houseResult, result);
	}
}
