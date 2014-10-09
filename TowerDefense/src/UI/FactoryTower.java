package UI;

import org.omg.Dynamic.Parameter;

//import GameElements.BasicTower;
//import GameElements.FiringTower;
//import GameElements.FreezingTower;
//import GameElements.Tower;

import core.domain.warriors.defenders.towers.*;
import core.domain.warriors.defenders.towers.towerType.*;

public class FactoryTower {

	public Tower creatTower(){
		
		return new AncientTower();
	}
	
	//
/*	public Tower creatTower( TowerParameters newParameters) {
		Tower obj  = null;

	
		
		if (newParameters.towerType == 10) {
			newParameters.imagePath = "/tower1.png";
			newParameters.firingSpeed = 11;
			newParameters.range = 11;
			newParameters.towerCurrentLevel = 0;
			obj = new FreezingTower(newParameters);
		} else if (newParameters.towerType == 20) {
			newParameters.imagePath = "/tower2.png";
			newParameters.firingSpeed = 21;
			newParameters.range = 21;
			newParameters.towerCurrentLevel = 0;
			obj = new FiringTower(newParameters);

		} else if (newParameters.towerType == 30) {
			newParameters.firingSpeed = 31;
			newParameters.range = 31;
			newParameters.imagePath = "/tower3.png";
			newParameters.towerCurrentLevel = 0;
			obj = new BasicTower(newParameters);
		}

	
		return obj;
	}; */
	
	
	public void updateTowerParameters(){
		
		
	}

}
