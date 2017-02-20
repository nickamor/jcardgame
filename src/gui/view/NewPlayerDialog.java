package gui.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.SimplePlayer;
import model.interfaces.Player;

/**
 * A dialog for creating a new player
 * 
 * @author Nicholas Amor
 *
 */
public class NewPlayerDialog extends JDialog {
	/**
	 * Creates a new Player with a randomised ID from the given form details.
	 * 
	 * @author Nicholas Amor
	 *
	 */
	public class NewPlayerAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder builder = new StringBuilder();
			Random r = new Random();

			for (int i = 0; i < 7; i++) {
				builder.append(Integer.toString(r.nextInt(10)));
			}

			player = new SimplePlayer(builder.toString(), nameEntry.getSelectedItem().toString(),
					(Integer) pointsEntry.getValue());

			setVisible(false);
		}
	}

	private static final long serialVersionUID = -8424043123968736713L;

	private static final String[] DEFAULT_NAMES = { "Spider", "Bruno", "Dwayne", "Cherry", "Jethro", "Maria", "Violet",
			"Edina", "Suelee", "Jeremy", "Walter", "Lisa", "Emile", "Davey", "Delora", "Chen" };

	private static final int pointsMin = 100;
	private static final int pointsMax = 10000;
	private static final int pointsDefault = 1000;
	private static final int pointsStep = 100;

	private final JComboBox<String> nameEntry;
	private final JSpinner pointsEntry;
	private final JButton newPlayerButton;
	private Player player;

	public NewPlayerDialog(GameFrame frame) {
		super(frame, "New Player");

		// set up dialog
		setLayout(new GridLayout(3, 0));
		setSize(300, 200);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// set up dialog controls
		nameEntry = new JComboBox<String>(DEFAULT_NAMES);
		nameEntry.setEditable(true);
		nameEntry.setSelectedIndex(new Random().nextInt(DEFAULT_NAMES.length));

		SpinnerModel model = new SpinnerNumberModel(pointsDefault, pointsMin, pointsMax, pointsStep);
		pointsEntry = new JSpinner(model);

		newPlayerButton = new JButton("New Player");
		newPlayerButton.setDefaultCapable(true);

		newPlayerButton.addActionListener(new NewPlayerAction());

		getRootPane().setDefaultButton(newPlayerButton);

		// add controls to dialog
		JPanel nameEntryPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		nameEntryPanel.add(new JLabel("Name:"));
		nameEntryPanel.add(nameEntry);
		add(nameEntryPanel);

		JPanel pointsEntryPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		pointsEntryPanel.add(new JLabel("Starting points:"));
		pointsEntryPanel.add(pointsEntry);
		add(pointsEntryPanel);

		JPanel newPlayerButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		newPlayerButtonPanel.add(newPlayerButton);
		add(newPlayerButtonPanel);
	}

	/**
	 * Get the player created by the dialog
	 * 
	 * @return the created Player
	 */
	public Player getValue() {
		return player;
	}

}
