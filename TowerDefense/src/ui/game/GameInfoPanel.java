package ui.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameInfoPanel extends JPanel implements Runnable {

	Icon aIcon;
	int racePos;

	/**
	 * Create the panel.
	 */
	public GameInfoPanel() {
		Dimension dim = getPreferredSize();
		dim.height = 75;
		setPreferredSize(dim);

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("knight.png").getFile());
		this.aIcon = new ImageIcon(file.getPath());
		racePos = 50;

	}

	public void run() {
		int waitTime = (int) (500);
		
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			if (racePos > 200){
				racePos = 50;
			}
			racePos++;
		}
	}

//	public void paint(Graphics g) {
//		
//		aIcon.paintIcon(this, g, racePos, 0);
//	}
	
	public void paintComponent(Graphics g) {
	super.paintComponent(g);
	aIcon.paintIcon(this, g, racePos, 0);
}

}
