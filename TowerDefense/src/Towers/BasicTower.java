package Towers;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import maps.GridCell;
import bullet.Bullet;
import bullet.Bullet.State;
import UI.TowerParameters;

/*
 *This class observes the state of all the cells that are under its ranges <br>
 *Whenever there is a critter in one of that cells, cell notifies all the tower
 *
 */
public class BasicTower implements TowerInterface, Observer {
	private ShootingBehaviour shoot;
	int rangeInCells = 1;
	Double speed = 15.0;
	int priceToUpgrade = 10;
	Double priceToCell = 5.0;
	int level = 0;
	long lastShootingTime = 0;
	public String imagePath = "";
	public BufferedImage image;
	public TowerParameters parameters;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public final ArrayList<Bullet> getBullets() { return bullets; }
	GridCell targetPoint;
	GridCell towerPosition;
	TowerDataCollection data = new TowerDataCollection();

	public BasicTower() {
		targetPoint = new GridCell(0, 0);
	};

	@Override
	public int getRange() {

		return rangeInCells;
	}

	@Override
	public Double getSpeed() {

		return speed;
	};

	@Override
	public void setPosition(GridCell cell) {

		towerPosition = cell;

	}

	@Override
	public GridCell getPosition() {

		return towerPosition;
	}

	public void updateBullets(int millisecPassed) {

		if (bullets != null) {
			for (Bullet bullet : bullets)
				bullet.updatePosition(millisecPassed);

		}
	}

	private Point createPoint(GridCell cell) {
		return new Point(cell.j * 30 + 15, cell.i * 30 + 15);
	}

	public void draw(Graphics g) {
		g.drawImage(image, towerPosition.j * 30, towerPosition.i * 30, null);
		for (int i = 0; i < bullets.size(); i++)
			if (bullets.get(i).getState() == Bullet.State.FINISHED)
				bullets.remove(i);

		for (Bullet bullet : bullets) {

			bullet.draw(g);
		}

	};

	public int levelUpDown(String upgradeLevel) {
		int level = 0;

		if (upgradeLevel.equals("up")) {

			/*
			 * 
			 * ArrayList<TowerParameters> levelConfig = data.towerLevelConfig
			 * .get(parameters.towerType); if (parameters.towerCurrentLevel + 1
			 * < levelConfig.size()) { TowerParameters newValues =
			 * levelConfig.get(parameters.towerCurrentLevel + 1);
			 * parameters.towerType = newValues.towerType;
			 * parameters.firingSpeed = newValues.firingSpeed; parameters.range
			 * = newValues.range; // number of affected cells
			 * parameters.salePrice = newValues.salePrice; parameters.buyPrice=
			 * newValues.buyPrice; parameters.imagePath = newValues.imagePath;
			 * parameters.towerCurrentLevel = parameters.towerCurrentLevel + 1;
			 * level = parameters.towerCurrentLevel; }
			 */

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
				parameters.buyPrice = newValues.buyPrice;
				parameters.imagePath = newValues.imagePath;
				parameters.towerCurrentLevel = parameters.towerCurrentLevel - 1;
				level = parameters.towerCurrentLevel;
			} else {
				// if the level is already <0> means we have to remove the tower
				// from the map
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

	@Override
	public void update(Observable arg0, Object arg1) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShootingTime > 1000) {
			targetPoint = ((GridCell) arg0);

			shoot.shoot(this);

			lastShootingTime = currentTime;
		}

	}

	@Override
	public int getUgradePrice() {
		// TODO Auto-generated method stub
		return priceToUpgrade;
	}

	@Override
	public Double getSellPrice() {
		// TODO Auto-generated method stub
		return priceToCell;
	}

	@Override
	public int getCurrentLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public GridCell getTargetPoint() {
		// TODO Auto-generated method stub
		return targetPoint;
	}

	@Override
	public long getLastShootingTime() {
		// TODO Auto-generated method stub
		return lastShootingTime;
	}

	@Override
	public GridCell getTarger() {
		// TODO Auto-generated method stub
		return targetPoint;
	}

	@Override
	public void setLastShootingTime(long time) {
		lastShootingTime = time;

	}

	@Override
	public void setTarget(GridCell newtarget) {
		targetPoint = newtarget;

	}

	@Override
	public void shoot(TowerInterface tower, GridCell newTargetPoint) {
		targetPoint = newTargetPoint;
		shoot.shoot(tower);

	}

	@Override
	public void addBullet(Bullet newBullet) {
		bullets.add(newBullet);
	}

	@Override
	public void SetShootingStrategy(ShootingBehaviour newbehaviour) {
		shoot = newbehaviour;

	}

}
