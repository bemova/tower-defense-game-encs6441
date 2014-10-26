package UI;

import javax.swing.SwingUtilities;

import UI.editor.*;

public class MapEditor {

	private MapEditor() {
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
