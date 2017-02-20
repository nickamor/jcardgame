package gui.view.widgets;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import gui.controller.ConfirmQuitAction;
import gui.controller.JoinGameAction;
import gui.controller.NewGameAction;
import gui.view.GameFrame;

/**
 * Game interface menu bar.
 * 
 * @author Nicholas Amor
 *
 */
public class GameMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3418981689960398435L;
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem newGameMenuItem = new JMenuItem("New Game");
	private JMenuItem hostGameMenuItem = new JMenuItem("Host Game...");
	private JMenuItem joinGameMenuItem = new JMenuItem("Join Game...");
	private JMenuItem exitMenuItem = new JMenuItem("Exit...");

	private void setShortcuts(JMenuItem menuItem, int mnemonic) {
		menuItem.setMnemonic(mnemonic);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(mnemonic, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
	}

	public GameMenuBar(GameFrame frame) {
		hostGameMenuItem.setEnabled(false);

		fileMenu.setMnemonic(KeyEvent.VK_F);

		setShortcuts(newGameMenuItem, KeyEvent.VK_N);
		setShortcuts(hostGameMenuItem, KeyEvent.VK_H);
		setShortcuts(joinGameMenuItem, KeyEvent.VK_J);
		setShortcuts(exitMenuItem, KeyEvent.VK_X);

		fileMenu.add(newGameMenuItem);
		fileMenu.add(hostGameMenuItem);
		fileMenu.add(joinGameMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		this.add(fileMenu);

		newGameMenuItem.addActionListener(new NewGameAction());
		joinGameMenuItem.addActionListener(new JoinGameAction());
		exitMenuItem.addActionListener(new ConfirmQuitAction(frame));
	}
}
