package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.Point;

import bullet.Bullet;

public class FireShooting implements ShootingBehaviour {
	private Double bulletMovingSpeed = 15.0;

	public FireShooting() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(TowerInterface tower) {

		createBullet(tower);
	}

	public void createBullet(TowerInterface tower) {

		Bullet newBulet = new Bullet(new Point(tower.getPosition().j * 30 + 15,
				tower.getPosition().i * 30 + 15), new Point(
				tower.getTargetPoint().j * 30 + 15,
				tower.getTargetPoint().i * 30 + 15), tower.getTargetPoint());
		newBulet.setSpeed(bulletMovingSpeed);
		tower.addBullet(newBulet);
		// newTower.bullets.add(newBulet );

		// return bullet;
	}
}
