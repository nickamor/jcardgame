package gui.view.widgets;

import javax.swing.JButton;
import javax.swing.JToolBar;

import gui.controller.JoinGameAction;
import gui.controller.NewGameAction;
import gui.controller.RefreshViewAction;
import gui.view.GameFrame;

/**
 * Game interface tool bar.
 * 
 * @author Nicholas Amor
 *
 */
public class GameToolBar extends JToolBar {
	private static final long serialVersionUID = 2407082056912176533L;
	private JButton newGame = new JButton("New Game");
	private JButton joinGame = new JButton("Join Game");
	private JButton refresh = new JButton("Refresh");

	public GameToolBar(GameFrame frame) {
		newGame.addActionListener(new NewGameAction());
		joinGame.addActionListener(new JoinGameAction());
		refresh.addActionListener(new RefreshViewAction(frame));
		
		add(newGame);
		add(joinGame);
		add(refresh);
	}
}
