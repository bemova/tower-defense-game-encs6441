package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Dummy extends JFrame {
private MapEditorUserInterface panel; 
	Dummy(){
		setTitle("Dummy");
		panel = new MapEditorUserInterface();
		add(panel,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 500));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(600, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Dummy();
				
			}
		});
			}

}
