package UI;

import org.omg.Dynamic.Parameter;

import core.domain.warriors.defenders.towers.vikiTowers.BasicTower;
import core.domain.warriors.defenders.towers.vikiTowers.FiringTower;
import core.domain.warriors.defenders.towers.vikiTowers.FreezingTower;
import core.domain.warriors.defenders.towers.vikiTowers.Tower;
import core.domain.warriors.defenders.towers.vikiTowers.TowerDataCollection;

public class FactoryTower {
	TowerDataCollection towerData = new TowerDataCollection();

	/*
	 * public Tower creatTower(){
	 * 
	 * return new AncientTower(); }
	 */

	//
	public Tower creatTower(TowerParameters newParameters) {
		Tower obj = null;

		TowerParameters configParams = towerData.towerLevelConfig.get(
				newParameters.towerType).get(newParameters.towerCurrentLevel);

		newParameters.buyPrice = configParams.buyPrice;
		newParameters.salePrice = configParams.salePrice;
		newParameters.imagePath = configParams.imagePath;
		newParameters.firingSpeed = configParams.firingSpeed;
		newParameters.range = configParams.range;

		if (newParameters.towerType == 10) {
			obj = new FreezingTower(newParameters);
		} else if (newParameters.towerType == 20) {
			obj = new FiringTower(newParameters);
		} else if (newParameters.towerType == 30) {
			obj = new BasicTower(newParameters);
		}

		return obj;
	};

	public void updateTowerParameters() {

	}

}
