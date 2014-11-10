package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import UI.TowerParameters;

import javax.imageio.ImageIO;

import core.domain.maps.GridCell;
import bullet.Bullet;
import bullet.Bullet.State;

import java.awt.Point;

public class Tower implements Observer {

	BufferedImage image;
	public TowerParameters parameters;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//	Bullet bullet;
	GridCell towerPosition;
	TowerDataCollection data = new TowerDataCollection();
	

	 Tower() {
	
	};

	public void setPosition(GridCell cell){
		
		towerPosition = cell;

	}

	public GridCell getPosition(){
		
		return towerPosition;
	}
	
	public void createBullet(Point newDestination){
		bullets.add( new Bullet(new Point(towerPosition.j * 30 + 15, towerPosition.i * 30 + 15), new Point(newDestination.x * 30 + 15 , newDestination.y * 30 + 15)));

		//return bullet;
	}
	public void updateBullets(int millisecPassed){

		if(bullets != null){
			for(Bullet bullet: bullets)
				bullet.updatePosition(millisecPassed);
		
		}
	}
	
	private Point createPoint(GridCell cell) {
		return new Point(cell.j * 30 + 15, cell.i * 30 + 15);
	}
	
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
		State state ;
		g.drawImage(image, parameters.position.x * 30,
				parameters.position.y * 30, null);
		for(int i = 0; i < bullets.size(); i++)
			if(bullets.get(i).getState() == Bullet.State.FINISHED)
				bullets.remove(i);
				
		for(Bullet bullet: bullets){
			
			bullet.draw(g); 
		}
		
	};

	public int levelUpDown(String upgradeLevel) {
		int level = 0;
		
		
		if (upgradeLevel.equals("up")) {
// if we want to increase the level of a tower
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
				level = parameters.towerCurrentLevel;
			}

		} else {// if we want to low the level of the tower
			
			if (parameters.towerCurrentLevel > 0) {
				// If the level of a tower is not <0> yet we still can low it
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
				level = parameters.towerCurrentLevel;
			}else{
				//if the level is already <0> means we have to remove the tower from the map
				level = -1;
				
			}
		}

		try {
			image = ImageIO.read(this.getClass().getResource(
					parameters.imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return level;

	}


	long lastCreationTime = 0;
	@Override
	public void update(Observable arg0, Object arg1) {
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastCreationTime > 1000)
		{
			Point destination = ((GridCell)arg0).returnPosition();
			createBullet(destination);
			lastCreationTime = currentTime;
		}
		
	}

}
