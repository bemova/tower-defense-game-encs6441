package core.applicationService.warriorServices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.warriors.defenders.towers.features.FirePower;
import core.domain.warriors.defenders.towers.features.FireRange;
import core.domain.warriors.defenders.towers.features.FireSpeed;
import core.domain.warriors.defenders.towers.towerType.AncientTower;
import core.domain.warriors.defenders.towers.towerType.KingTower;
import core.domain.warriors.defenders.towers.towerType.ModernTower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;
/**
 * <b>this class is used as a application service to create a difrent type of factories by difrent features</b>
 * @author mojtaba
 * @version 0.1
 */
public class TowerFactory {

	/**
	 * it takes tower type name and it will create a type of tower without any features
	 * @param towerType
	 * @return Tower 
	 */
	public Tower getTower(String towerType) {
		if (towerType == null) {
			return null;
		}
		if (towerType.equalsIgnoreCase("ModernTower")) {
			return new ModernTower();
		} else if (towerType.equalsIgnoreCase("AncientTower")) {
			return new AncientTower();
		} else if (towerType.equalsIgnoreCase("KingTower")) {
			return new KingTower();
		}
		return null;
	}
	/**
	 * it creates a tower by tower type name and tower level that can define the feature numbers as a default
	 * @param towerType
	 * @param level , it is a type of TowerLevel that is a enum type that has level one, two and three
	 * @return Tower
	 */
	public Tower getTower(String towerType, TowerLevel level) {
		Tower tower = getTower(towerType);
		try {
			switch (level.name()) {
			case "one":
				for (int i = 0; i < 1; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.one);
				break;

			case "two":
				for (int i = 0; i < 2; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.two);
				break;

			case "three":
				for (int i = 0; i < 3; i++)
					tower = getLevelOne(tower);
				tower.setLevel(TowerLevel.three);
				break;

			}
			return tower;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * it can create simple basic decorated tower in level one that means it has fire speed and range and power 1 1 
	 * @param tower
	 * @return Tower
	 */
	Tower getLevelOne(Tower tower) {
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

	/**
	 * <b>create a decorated tower by list of contributors tower </b> 
	 * @param details, is a list of Tower that can have a contribute to create a decorated tower 
	 * @return Tower
	 */
	public Tower getDecoratedTower(List<Tower> details) {
		Tower baseTower = null;
		try {
			for (Tower tower : details) {
				if (tower.getClass() == ModernTower.class) {
					baseTower = new ModernTower();
					break;
				} else if (tower.getClass() == AncientTower.class) {
					baseTower = new AncientTower();
					break;
				} else if (tower.getClass() == KingTower.class) {
					baseTower = new KingTower();
					break;
				}
			}
			for (Tower tower : details) {
				switch (tower.getClass().getSimpleName()) {
				case "FireSpeed":
					if (baseTower != null)
						baseTower = new FireSpeed(baseTower);
					break;
				case "FirePower":
					if (baseTower != null)
						baseTower = new FirePower(baseTower);
					break;
				case "FireRange":
					if (baseTower != null)
						baseTower = new FireRange(baseTower);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return baseTower;
	}
	/**
	 * <b> this method can get a list of contributor that can create a decorated tower with infinitive features
	 *  an it returns the list of key value pair that contains the features' name as a key and features' count as a value</b> 
	 * @param towerDetails
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getFeaturesCount(List<Tower> towerDetails) {
		Map<String, Integer> details = new HashMap<>();
		details.put("FirePower", 0);
		details.put("FireRange", 0);
		details.put("FireSpeed", 0);
		try {
			for (Object tower : towerDetails) {
				if (tower instanceof TowerFeatureDecorator) {
					String key = tower.getClass().getSimpleName();
					details.put(key, details.get(key) + 1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return details;
	}
	/**
	 * 
	 * @param towerDetails
	 * @return
	 */
	public String getDecoratedName(List<Tower> towerDetails) {
		try {
			for (Object tower : towerDetails) {
				String key = tower.getClass().getSimpleName();
				if(key.equals(DefenderConstants.MODERN_TOWER_TYPE) ||
						key.equals(DefenderConstants.ANCIENT_TOWER_TYPE) ||
						key.equals(DefenderConstants.KING_TOWER_TYPE)){
					return key;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * <b>it used in update tower in our grid with freatures count</b>
	 * @param towerType
	 * @param speedCount
	 * @param rangeCount
	 * @param powerCount
	 * @return Tower 
	 */
	public Tower updateLevel(String towerType, int speedCount, int rangeCount,
			int powerCount) {

		TowerFactory factory = new TowerFactory();
		Tower tower = factory.getTower(towerType);

		for (int i = 0; i < rangeCount; i++){
			tower = new FireRange(tower);
		}
		for (int i = 0; i < speedCount; i++){
			tower = new FireSpeed(tower);
		}
		for (int i = 0; i < powerCount; i++){
			tower = new FirePower(tower);
		}

		return tower;
	}

}