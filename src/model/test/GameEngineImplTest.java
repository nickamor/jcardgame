package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class GameEngineImplTest {
	
	private GameEngine engine;
	private Player player;

	@Before
	public void setUp() throws Exception {
		engine = new GameEngineImpl();
		player = new SimplePlayer("testID", "testPlayer", 1000);
	}

	@Test
	public void testAddPlayer() {
//		fail("Not yet implemented");
		engine.addPlayer(player);
		
		Player testPlayer = engine.getPlayer(player.getPlayerId());
		
		assertEquals(player, testPlayer);
		
		engine.removePlayer(player);
		
		Player nullPlayer = engine.getPlayer(player.getPlayerId());
		
		assertNull(nullPlayer);
	}

}
