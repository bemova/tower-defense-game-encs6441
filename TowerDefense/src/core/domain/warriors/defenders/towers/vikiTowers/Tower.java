package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import UI.TowerParameters;

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

	public void levelUpDown(String newLevel) {

		if (newLevel.equals("up")) {
			// parameters.update(data.towerLevelConfig.get(parameters.towerType).get(
			// parameters.towerCurrentLevel + 1));

			ArrayList<TowerParameters> levelConfig = data.towerLevelConfig
					.get(parameters.towerType);
			if (parameters.towerCurrentLevel + 1 < levelConfig.size()) {
				TowerParameters newValues = levelConfig.get(parameters.towerCurrentLevel + 1);
				parameters.towerType = newValues.towerType;
				parameters.firingSpeed = newValues.firingSpeed;
				parameters.range = newValues.range; // number of affected cells
				parameters.salePrice = newValues.salePrice;
				parameters.buyPrice= newValues.buyPrice;
				parameters.imagePath = newValues.imagePath;
				parameters.towerCurrentLevel = parameters.towerCurrentLevel + 1;
			}

		} else {

			if (parameters.towerCurrentLevel > 0) {
				TowerParameters newValues = data.towerLevelConfig.get(
						parameters.towerType).get(
						parameters.towerCurrentLevel - 1);
				parameters.towerType = newValues.towerType;
				parameters.firingSpeed = newValues.firingSpeed;
				parameters.range = newValues.range; // number of affected cells
				parameters.salePrice = newValues.salePrice;
				parameters.buyPrice= newValues.buyPrice;
				parameters.imagePath = newValues.imagePath;
				parameters.towerCurrentLevel = parameters.towerCurrentLevel - 1;
			}
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
