package GameElements;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameContraller.TowerParameters;

public class Tower {
	
	BufferedImage image;
	TowerParameters parameters;

	public Tower() {
	};

	public Tower(TowerParameters newParameters) {
		
		parameters = newParameters;
		
		try {
			image = ImageIO.read(this.getClass().getResource(parameters.immagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	public void draw( Graphics g){
		
		g.drawImage(image, parameters.position.x * 30, parameters.position.y * 30, null);
		
	};
}
