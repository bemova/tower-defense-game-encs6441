package Towers;

import java.awt.Point;

import bullet.Bullet;
import bullet.FireBullet;
import bullet.IceBullet;

public class IceShooting implements ShootingBehaviour {

	private Double bulletMovingSpeed = 10.0;
	
	public IceShooting() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(TowerInterface tower) {
		
		createBullet(tower);
	}

	public void createBullet(TowerInterface tower) {

		Bullet newBulet = new IceBullet(new Point(tower.getPosition().j * 30 + 15,
				tower.getPosition().i * 30 + 15), new Point(
				tower.getTargetPoint().j * 30 + 15,
				tower.getTargetPoint().i * 30 + 15), tower.getTargetPoint());
		newBulet.setSpeed(bulletMovingSpeed);
		tower.addBullet(newBulet);

	}


}
