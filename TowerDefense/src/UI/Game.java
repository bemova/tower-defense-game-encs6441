package UI;

import javax.swing.SwingUtilities;

import UI.game.MainFrame;

public class Game {

	private Game() {
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();

			}
		});
	}
}
