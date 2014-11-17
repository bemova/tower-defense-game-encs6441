package ui.game;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CritterShape extends JComponent {

	/**
	 * This class is responsible for drawing a critter. It receives an image (icon) and the coordinates of where it's supposed to be displayed.
	 */
	public CritterShape() {

	}

	/**
	 * this method draws the image.
	 * @param g Graphics component
	 * @param image Critter image
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void draw(Graphics g, Icon image, int x, int y){
		super.paintComponent(g);
		image.paintIcon(this, g, x, y);


	}
}
