package core.domain.warriors.defenders.towers;

import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class DEcoDemo {
	public static void main(String[] args) {
		AncientTower ancientTower = new AncientTower();
		ancientTower.performMovingBehaviour();
		ancientTower.performShootingBehaviour();
		ancientTower.performSoundBehaviour();
		System.out.println(" this ancient tower price is :"+ ancientTower.cost());
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		ModernTower modernTower = new ModernTower();
		Tower especialTower = new FireRange(modernTower);
		System.out.println(especialTower.cost());
		especialTower = new FireRange(especialTower);
		System.out.println(especialTower.cost());
		especialTower = new FireRange(especialTower);
		System.out.println(especialTower.cost());
		System.out.println(ancientTower.Id);
		System.out.println(especialTower.getDescription());//));
		
		
	}

}
