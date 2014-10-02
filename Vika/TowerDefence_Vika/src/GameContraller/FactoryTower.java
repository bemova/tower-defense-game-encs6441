package GameContraller;

import org.omg.Dynamic.Parameter;

import GameElements.BasicTower;
import GameElements.FiringTower;
import GameElements.FreezingTower;
import GameElements.Tower;

public class FactoryTower {

	
	public Tower creatTower( TowerParameters newParameters) {
		Tower obj  = null;

	
		
		if (newParameters.towerType == 10) {
			newParameters.immagePath = "/tower1.png";
			obj = new FreezingTower(newParameters);
		} else if (newParameters.towerType == 20) {
			newParameters.immagePath = "/tower2.png";
			obj = new FiringTower(newParameters);

		} else if (newParameters.towerType == 30) {
			newParameters.immagePath = "/tower3.png";
			obj = new BasicTower(newParameters);
		}

		
		
		
		return obj;
	};

}
