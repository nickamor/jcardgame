package gui.view.widgets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.view.GameFrame;
import model.interfaces.Player;

/**
 * List of {@link Player}s in current game.
 * 
 * @author Nicholas Amor
 *
 */
public class PlayersListPanel extends JPanel {
	private static final long serialVersionUID = -6054138753926309707L;

	DefaultListModel<String> playerNames = new DefaultListModel<>();
	JList<String> playersList = new JList<>(playerNames);

	public PlayersListPanel(GameFrame frame) {
		super(new GridLayout());
		setOpaque(false);
		setMinimumSize(new Dimension(150, 200));
		setPreferredSize(new Dimension(200, 300));

		JScrollPane scroll = new JScrollPane(playersList);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		scroll.getViewport().setOpaque(false);

		playersList.setOpaque(false);
		((DefaultListCellRenderer) playersList.getCellRenderer()).setOpaque(false);
		playersList.setForeground(GameFrame.foregroundColor());
		playersList.setFont(playersList.getFont().deriveFont(Font.BOLD));

		add(scroll);
	}

	public void setPlayers(Collection<Player> players) {
		playerNames.clear();

		for (Player player : players) {
			playerNames.addElement(String.format("%s - %d", player.getPlayerName(), player.getPoints()));
		}
	}
}
