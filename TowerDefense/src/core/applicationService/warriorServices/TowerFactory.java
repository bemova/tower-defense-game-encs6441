package core.applicationService.warriorServices;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
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
		}
		return null;
	}
}