package gui.view.widgets;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import gui.view.CardResources;
import model.interfaces.PlayingCard;

/**
 * A panel that displays a collection of PlayingCards.
 * 
 * @see CardPanel
 * @author Nicholas Amor
 *
 */
public class CardsPanel extends JScrollPane {
	private static final long serialVersionUID = 2431959041597940600L;

	private final JPanel cards;

	private boolean dirty = false;

	public CardsPanel() {
		setOpaque(false);
		setBorder(null);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		cards = new JPanel();
		cards.setOpaque(false);

		JViewport viewport = new JViewport();
		viewport.setView(cards);
		viewport.setOpaque(false);
		setViewport(viewport);

		// warm up card resources
		CardResources.getPreferredSize();
	}

	public void clear() {
		cards.removeAll();
		cards.repaint();
	}

	public void addPlayingCard(PlayingCard card, boolean dirty) {
		if (this.dirty) {
			clear();
		}

		cards.add(new CardPanel(card));
		cards.revalidate();

		JScrollBar bar = getHorizontalScrollBar();
		bar.setValue(bar.getMaximum());

		this.dirty = dirty;
	}
}
