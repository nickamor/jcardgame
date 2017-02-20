package gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import gui.controller.ConfirmQuitWindowAdapter;
import gui.controller.FrameReadyTask;
import gui.controller.KeyboardShortcutsListener;
import gui.view.widgets.GameControlPanel;
import gui.view.widgets.GameMenuBar;
import gui.view.widgets.GameToolBar;
import gui.view.widgets.GameViewPanel;
import gui.view.widgets.PlayersListPanel;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Main game interface
 * 
 * @author Nicholas Amor
 *
 */
public class GameFrame extends JFrame {
	private static final long serialVersionUID = -2073461776897158302L;

	public static final int delay = 300;

	private final GameMenuBar menubar;
	private final GameToolBar toolbar;
	private final GameViewPanel view;
	private final GameControlPanel control;
	private final PlayersListPanel players;

	private final GameEngine engine;
	private Player player;

	public static Color backgroundColor() {
		return new Color(27, 94, 32);
	}

	public static Color foregroundColor() {
		return Color.WHITE;
	}

	/**
	 * Create a new GameFrame with the default engine.
	 */
	public GameFrame() {
		this(new GameEngineImpl());
	}

	/**
	 * Create a new GameFrame with a specific GameEngine implementation.
	 * 
	 * @param engine
	 */
	public GameFrame(GameEngine engine) {
		if (engine == null) {
			throw new IllegalArgumentException("Can not start without a GameEngine.");
		}
		this.engine = engine;

		/** initialise interface **/
		menubar = new GameMenuBar(this);
		toolbar = new GameToolBar(this);
		view = new GameViewPanel();
		control = new GameControlPanel(this);
		players = new PlayersListPanel(this);

		setLayout(new BorderLayout());
		setSize(1024, 800);
		setMinimumSize(new Dimension(800, 600));
		setResizable(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(backgroundColor());

		setJMenuBar(menubar);
		add(toolbar, BorderLayout.NORTH);
		add(view, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		add(players, BorderLayout.EAST);

		addWindowListener(new ConfirmQuitWindowAdapter(this));
		addKeyListener(new KeyboardShortcutsListener(this));

		setVisible(true);

		getRootPane().setDefaultButton(control.getDealButton());

		/** initialise game */
		Player player = promptForNewPlayer();

		if (player != null) {
			setPlayer(player);

			new FrameReadyTask(this).execute();
		} else {
			setVisible(false);
			dispose();
		}
	}

	/**
	 * Shows a dialog for creating a new {@link Player}.
	 * 
	 * @return A new Player, or null if the dialog was cancelled.
	 */
	public Player promptForNewPlayer() {
		NewPlayerDialog newPlayerDialog = new NewPlayerDialog(this);
		newPlayerDialog.setVisible(true);
		Player player =  newPlayerDialog.getValue();
		newPlayerDialog.dispose();
		
		return player;
	}

	/**
	 * Get the {@link GameControlPanel} of this {@link GameFrame}.
	 * 
	 * @return
	 */
	public GameControlPanel getGameControl() {
		return control;
	}

	/**
	 * Get the {@link GameViewPanel} of this {@link GameFrame}.
	 * 
	 * @return
	 */
	public GameViewPanel getGameView() {
		return view;
	}

	/**
	 * Get the {@link Player} of this {@link GameFrame}.
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Set/update the player that this view is observing.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameEngine getGameEngine() {
		return engine;
	}

	public PlayersListPanel getPlayersList() {
		return players;
	}
}
