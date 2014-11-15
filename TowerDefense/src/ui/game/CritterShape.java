package ui.game;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

public class CritterShape extends JComponent {

	public CritterShape() {

	}

	public void draw(Graphics g, Icon image, int x, int y){
		super.paintComponent(g);
		image.paintIcon(this, g, x, y);


	}
}
