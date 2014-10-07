package core.domain.warriors.defenders.towers;

import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class Demo {
	public static void main(String[] args) {
		ModernTower modernTower = new ModernTower();
		modernTower.performMovingBehaviour();
		modernTower.performShootingBehaviour();
		modernTower.performSoundBehaviour();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5");
		AncientTower ancientTower = new AncientTower();
		ancientTower.performMovingBehaviour();
		ancientTower.performShootingBehaviour();
		ancientTower.performSoundBehaviour();
	}

}
