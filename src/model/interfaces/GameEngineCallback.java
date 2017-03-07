package model.interfaces;

public interface GameEngineCallback {

	void nextCard(Player player, PlayingCard card, GameEngine engine);

	void bustCard(Player player, PlayingCard card, GameEngine engine);

	void result(Player player, int result, GameEngine engine);

	void nextHouseCard(PlayingCard card, GameEngine engine);

	void houseBustCard(PlayingCard card, GameEngine engine);

	void houseResult(int result, GameEngine engine);

}
