package model.interfaces;

public interface Player {

	String getPlayerName();

	void setResult(int result);

	String getPlayerId();

	int getBet();

	int getResult();

	int getPoints();

	void setPoints(int i);

	boolean placeBet(int bet);

	void setPlayerName(String playerName);

}
