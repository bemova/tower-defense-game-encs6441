package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class TowerDataCollection {
	
	public HashMap<Integer, ArrayList<TowerParameters>> towerLevelConfig = new HashMap<Integer, ArrayList<TowerParameters>>();
	
	static private TowerParameters createParameter(int towerType, double firingSpeed, int range, 
			String immagePath, int towerCurrentLevel)
	{
		TowerParameters retVal = new TowerParameters();
		retVal.towerType = towerType; 
		retVal.firingSpeed = firingSpeed;
		retVal.range = range; // number of affected cells
		//retVal.position;
		retVal.imagePath = immagePath;
		retVal.towerCurrentLevel = towerCurrentLevel;;
		
		
		
		return retVal;
	}
	
	public TowerDataCollection(){
		ArrayList<TowerParameters> firing = new ArrayList<TowerParameters>();
		firing.add(createParameter(10, 1, 1, "/tower11.png", 0));
		firing.add(createParameter(10, 2, 2, "/tower12.png", 1));
		firing.add(createParameter(10, 3, 3, "/tower13.png", 2));		
		towerLevelConfig.put(10, firing);
		
		ArrayList<TowerParameters> freezing = new ArrayList<TowerParameters>();
		freezing.add(createParameter(20, 21, 221, "/tower2.png", 0));
		freezing.add(createParameter(20, 33, 333, "/tower21.png", 1));
		
		towerLevelConfig.put(20, freezing);
		
		ArrayList<TowerParameters> basic = new ArrayList<TowerParameters>();
		basic.add(createParameter(30, 1111, 11111, "/tower3.png", 0));
		basic.add(createParameter(30, 2, 2, "/tower31.png", 1));
		
		towerLevelConfig.put(30, basic);
	}
	
}
