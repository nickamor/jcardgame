package gui.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import model.interfaces.GameEngine;
import networking.GameEngineClientStub;
import networking.GameEngineServerStub;

/**
 * A modal dialog for connecting to a remote game.
 * 
 * @author Nicholas Amor
 *
 */
public class JoinServerDialog extends JDialog {
	/**
	 * Attempts to connect to network game.
	 * 
	 * @author Nicholas Amor
	 *
	 */
	public class ConnectAction implements ActionListener {
		public class ConnectButtonWorker extends SwingWorker<GameEngineClientStub, Void> {
			private final String host;
			private final int port;

			public ConnectButtonWorker(String host, int port) {
				this.host = host;
				this.port = port;
			}

			@Override
			protected GameEngineClientStub doInBackground() throws Exception {
				return new GameEngineClientStub(host, port);
			}

			@Override
			protected void done() {
				try {
					engine = get();

					setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					JOptionPane.showMessageDialog(null, "Could not connect to server: " + e.getCause().getMessage());
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new ConnectButtonWorker(address.getText(), Integer.parseInt(port.getText())).execute();
		}
	}

	/**
	 * Verifies port is valid.
	 * 
	 * @author Nicholas Amor
	 *
	 */
	class PortInputVerifier extends InputVerifier {

		@Override
		public boolean verify(JComponent input) {
			try {
				String inputStr = ((JTextField) input).getText();
				int inputVal = Integer.parseInt(inputStr);
				if (inputVal > 0 && inputVal < 65535) {
					return true;
				}
			} catch (NumberFormatException | ClassCastException e) {
				return false;
			}
			return false;
		}

	}

	private static final long serialVersionUID = 7553728897943936657L;

	private GameEngine engine;

	private JTextField port;

	private JTextField address;

	public JoinServerDialog() {
		setTitle("Join Game");
		setLayout(new GridLayout(3, 0));
		setSize(300, 200);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel addressLabel = new JLabel("Address:");
		address = new JTextField("localhost");

		JLabel portLabel = new JLabel("Port:");
		port = new JTextField(Integer.toString(GameEngineServerStub.DEFAULT_PORT));
		port.setInputVerifier(new PortInputVerifier());

		JButton connect = new JButton("Connect");
		connect.addActionListener(new ConnectAction());
		
		getRootPane().setDefaultButton(connect);

		JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		addressPanel.add(addressLabel);
		addressPanel.add(address);
		add(addressPanel);

		JPanel portPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		portPanel.add(portLabel);
		portPanel.add(port);
		add(portPanel);

		JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		startPanel.add(connect);
		add(startPanel);
	}

	/**
	 * Get the GameEngine network proxy.
	 * 
	 * @return
	 */
	public GameEngine getValue() {
		return engine;
	}

}
