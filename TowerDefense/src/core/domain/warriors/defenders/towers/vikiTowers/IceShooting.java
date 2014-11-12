package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.Point;

import bullet.Bullet;

public class IceShooting implements ShootingBehaviour {

	Double bulletMovingSpeed = 50.0;
	
	public IceShooting() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot(TowerInterface tower) {
		
		createBullet(tower);
	}

	public void createBullet(TowerInterface tower){
		
	/*	BasicTower newTower = ((BasicTower)tower);
		Bullet newBulet = new Bullet(new Point(tower.getPosition().j * 30 + 15, tower.getPosition().i * 30 + 15), new Point( tower.getTargetPoint().j * 30 + 15 , tower.getTargetPoint().i* 30 + 15));
		newBulet.setSpeed(bulletMovingSpeed);
		newTower.bullets.add(newBulet ); */

		//return bullet;
	}


}
