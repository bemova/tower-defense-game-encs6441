package core.applicationService.warriorServices;

import java.util.List;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.KingTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;

public class TowerFactory {

	//use getShape method to get object of type shape 
	public Tower getTower(String towerType){
		if(towerType == null){
			return null;
		}		
		if(towerType.equalsIgnoreCase("ModernTower")){
			return new ModernTower();
		} else if(towerType.equalsIgnoreCase("AncientTower")){
			return new AncientTower();
		}else if(towerType.equalsIgnoreCase("KingTower")){
			return new KingTower();
		}
		return null;
	}
	public Tower getDecoratedTower(List<Tower> details){
		Tower baseTower = null;
		Tower decoratedTower = null;
		try {
			for (Tower tower : details) {
				if(tower.getClass() == ModernTower.class){
					baseTower = new ModernTower();
					break;
				}else if (tower.getClass() == AncientTower.class) {
					baseTower = new AncientTower();
					break;
				}else if (tower.getClass() == KingTower.class) {
					baseTower = new KingTower();
					break;
				}
			}
			for (Tower tower : details) {
				switch (tower.getClass().getSimpleName()) {
				case "FireSpeed":
					if(baseTower != null)
						decoratedTower = new FireSpeed(baseTower);
					break;
				case "FirePower":
					if(baseTower != null)
						decoratedTower = new FirePower(baseTower);
					break;
				case "FireRange":
					if(baseTower != null)
						decoratedTower = new FireRange(baseTower);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return decoratedTower;
	}
}