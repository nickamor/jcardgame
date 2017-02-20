package model;

import java.io.Serializable;

import model.interfaces.PlayingCard;

/**
 * Implementation of {@link PlayingCard} interface.
 * 
 * @author Nicholas Amor Amor
 *
 */
public class PlayingCardImpl implements PlayingCard, Serializable {
	private static final long serialVersionUID = -489095503759976869L;

	private final Suit suit;
	private final Value value;

	public PlayingCardImpl(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int getScore() {
		switch (value) {

		case King:
		case Queen:
		case Jack:
		case Ten:
			return 10;

		case Two:
			return 2;
		case Three:
			return 3;
		case Four:
			return 4;
		case Five:
			return 5;
		case Six:
			return 6;
		case Seven:
			return 7;
		case Eight:
			return 8;
		case Nine:
			return 9;

		case Ace:
			return 1;

		default:
			return 0;
		}
	}

	@Override
	public String toString() {
		return String.format("Suit: %s, Value: %s", value.toString(), suit.toString());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof PlayingCard && ((PlayingCard) obj).getSuit() == suit
				&& ((PlayingCard) obj).getValue() == value);
	}
}
