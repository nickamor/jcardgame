package gui.view.widgets;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import gui.controller.BetAction;
import gui.controller.DealAction;
import gui.view.GameFrame;

/**
 * Player status, bet and deal buttons, and game log.
 * 
 * @author Nicholas Amor
 *
 */
public class GameControlPanel extends JPanel {
	private static final long serialVersionUID = -6119048930823492851L;
	private final PlayerStatusPanel playerStatus;
	private final JButton betButton = new JButton("Bet");
	private final JButton dealButton = new JButton("Deal");
	private final JTextArea gamelog = new JTextArea();

	public GameControlPanel(GameFrame frame) {
		// initialise components
		super(new GridLayout(0, 4));

		playerStatus = new PlayerStatusPanel(frame);

		// set component properties
		setPreferredSize(new Dimension(1024, 100));

		betButton.addActionListener(new BetAction(frame, betButton));
		dealButton.addActionListener(new DealAction(frame, dealButton));

		gamelog.setEditable(false);
		gamelog.setOpaque(false);
		DefaultCaret caret = (DefaultCaret) gamelog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		// contain the log within a scrollpane to give it scrollbars
		JScrollPane logScrollPane = new JScrollPane(gamelog);
		logScrollPane.setBorder(null);
		logScrollPane.setOpaque(false);
		logScrollPane.getViewport().setOpaque(false);
		logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(playerStatus);
		add(betButton);
		add(dealButton);
		add(logScrollPane);
	}

	public void log(String format, Object... args) {
		String logText = gamelog.getText() + String.format(format, args) + '\n';
		gamelog.setText(logText);
	}

	public PlayerStatusPanel getPlayerStatus() {
		return playerStatus;
	}

	public JButton getBetButton() {
		return betButton;
	}

	public JButton getDealButton() {
		return dealButton;
	}
}
