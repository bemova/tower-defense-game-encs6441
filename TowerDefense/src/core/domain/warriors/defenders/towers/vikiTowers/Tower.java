package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Tower {

	BufferedImage image;
	public TowerParameters parameters;
	TowerDataCollection data = new TowerDataCollection();

	public Tower() {
	};

	public Tower(TowerParameters newParameters) {

		parameters = newParameters;

		try {
			image = ImageIO.read(this.getClass().getResource(
					parameters.imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void draw(Graphics g) {
		


		g.drawImage(image, parameters.position.x * 30,
				parameters.position.y * 30, null);

	};

	void levelUpDown(String newLevel) {

		if (newLevel.equals("up")){
		//	parameters.update(data.towerLevelConfig.get(parameters.towerType).get(
		//			parameters.towerCurrentLevel + 1));
			TowerParameters newValues = data.towerLevelConfig.get(parameters.towerType).get(
							parameters.towerCurrentLevel + 1);
					parameters.towerType = newValues.towerType; 
					parameters.firingSpeed = newValues.firingSpeed;
					parameters.range  = newValues.range; // number of affected cells
			
					parameters.imagePath = newValues.imagePath;
					parameters.towerCurrentLevel = parameters.towerCurrentLevel +1;
			
			
		}
		else{
			TowerParameters newValues = data.towerLevelConfig.get(parameters.towerType).get(
					parameters.towerCurrentLevel - 1);
			parameters.towerType = newValues.towerType; 
			parameters.firingSpeed = newValues.firingSpeed;
			parameters.range  = newValues.range; // number of affected cells
	
			parameters.imagePath = newValues.imagePath;
			parameters.towerCurrentLevel = parameters.towerCurrentLevel - 1;
	
		}

		try {
			image = ImageIO.read(this.getClass().getResource(
					parameters.imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
