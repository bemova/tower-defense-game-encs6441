package ui;

import javax.swing.SwingUtilities;

import ui.editor.MainFrame;

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
