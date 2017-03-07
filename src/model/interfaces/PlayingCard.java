package model.interfaces;

public interface PlayingCard {

	public enum Value {
		King, Queen, Jack, Ten, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ace
	}

	public enum Suit {
		clubs, diamonds, hearts, spades
	}

	int getScore();

	Suit getSuit();

	Value getValue();

}
