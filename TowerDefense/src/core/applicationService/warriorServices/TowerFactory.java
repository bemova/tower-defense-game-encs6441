package core.applicationService.warriorServices;

import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.KingTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;

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
	public Tower getTower(String towerType, TowerLevel level){
		Tower tower = getTower(towerType);
		try {
			switch (level.name()) {
			case "One":
				tower = getLevelOne(tower);
				break;
			case "two":
			{
				for(int i =0; i < 2;i++)
					tower = getLevelOne(tower);
				break;
			}
			case "three":
			{
				for(int i =0; i < 3;i++)
					tower = getLevelOne(tower);
				break;
			}
			}
			return tower;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	Tower getLevelOne(Tower tower){
		try {
			tower = new FirePower(tower);
			tower = new FireRange(tower);
			tower = new FireSpeed(tower);
			return tower;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Tower getDecoratedTower(List<Tower> details){
		Tower baseTower = null;
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
						baseTower = new FireSpeed(baseTower);
					break;
				case "FirePower":
					if(baseTower != null)
						baseTower = new FirePower(baseTower);
					break;
				case "FireRange":
					if(baseTower != null)
						baseTower = new FireRange(baseTower);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return baseTower;
	}
	public Map<String, Integer> getFeaturesCount(List<Tower> towerDetails){
		Map<String,Integer> details = new HashMap<>();
		try {
			for (Object tower : towerDetails) {
				if(tower instanceof FeatureDescriptor){
					String key = tower.getClass().getSimpleName();
					int value = details.get(key);
					value ++;
					details.put(key, value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return details;

	}
}