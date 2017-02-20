package gui.controller;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gui.view.GameFrame;

/**
 * Listens for graphical interface keyboard shortcuts.
 * 
 * @author Nicholas Amor
 *
 */
public class KeyboardShortcutsListener extends KeyAdapter {
	private final GameFrame frame;

	public KeyboardShortcutsListener(GameFrame frame) {
		this.frame = frame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) == 1
				&& e.getKeyCode() == KeyEvent.VK_W) {
			new ConfirmQuitRunner(frame).run();
		}
	}
}
