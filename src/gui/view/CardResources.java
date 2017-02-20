package gui.view;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import model.interfaces.PlayingCard;

/**
 * Helper functions for displaying graphical cards.
 * 
 * @author Nicholas Amor
 *
 */
public class CardResources {
	private static Map<String, ImageIcon> images = new HashMap<>();

	static {
		/**
		 * Load all card images.
		 */
		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				String filename = getImageFilename(suit, value);
				images.put(filename, loadImage(filename, getCardDescription(suit, value)));
			}
		}
	}

	/**
	 * Get the corresponding filename for the given suit and value.
	 * 
	 * @param suit
	 * @param value
	 * @return
	 */
	private static String getImageFilename(PlayingCard.Suit suit, PlayingCard.Value value) {
		String filename = "res/";

		switch (suit) {
		case clubs:
			filename += "clubs/";
			break;
		case diamonds:
			filename += "diamonds/";
			break;
		case hearts:
			filename += "hearts/";
			break;
		case spades:
			filename += "spades/";
			break;
		default:
			throw new IllegalArgumentException("Unexpected card suit");
		}

		switch (value) {
		case Ace:
			filename += "card01.png";
			break;
		case Two:
			filename += "card02.png";
			break;
		case Three:
			filename += "card03.png";
			break;
		case Four:
			filename += "card04.png";
			break;
		case Five:
			filename += "card05.png";
			break;
		case Six:
			filename += "card06.png";
			break;
		case Seven:
			filename += "card07.png";
			break;
		case Eight:
			filename += "card08.png";
			break;
		case Nine:
			filename += "card09.png";
			break;
		case Ten:
			filename += "card10.png";
			break;
		case Jack:
			filename += "card11.png";
			break;
		case Queen:
			filename += "card12.png";
			break;
		case King:
			filename += "card13.png";
			break;
		default:
			throw new IllegalArgumentException("Unexpected card value");
		}

		return filename;
	}

	/**
	 * Get the corresponding description for the given suit and value.
	 * 
	 * @param suit
	 * @param value
	 * @return
	 */
	private static String getCardDescription(PlayingCard.Suit suit, PlayingCard.Value value) {
		return String.format("%s of %s", value, suit);
	}

	/**
	 * Get the image from the given filename, and set the description.
	 * 
	 * @param filename
	 * @param description
	 * @return
	 */
	private static ImageIcon loadImage(String filename, String description) {
		Class<CardResources> thisClass = CardResources.class;

		try {
			return new ImageIcon(filename, description);
		} catch (Exception e) {
			Logger.getLogger(thisClass.getName()).log(Level.SEVERE, "Could not load card image", e);
		}

		return null;
	}

	/**
	 * Get the size of card resource images.
	 * 
	 * @return
	 */
	public static Dimension getPreferredSize() {
		return new Dimension(130, 200);
	}

	/**
	 * Get the image corresponding to the given card.
	 * 
	 * @param card
	 * @return
	 */
	public static ImageIcon getCardImageIcon(PlayingCard card) {
		return images.get(getImageFilename(card.getSuit(), card.getValue()));
	}

	/**
	 * Get the description corresponding to the given card.
	 * 
	 * @param card
	 * @return
	 */
	public static String getCardDescription(PlayingCard card) {
		return getCardDescription(card.getSuit(), card.getValue());
	}
}
