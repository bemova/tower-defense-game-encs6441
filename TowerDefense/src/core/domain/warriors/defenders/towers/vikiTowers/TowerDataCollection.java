package core.domain.warriors.defenders.towers.vikiTowers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import UI.TowerParameters;

public class TowerDataCollection {
	
	public HashMap<Integer, ArrayList<TowerParameters>> towerLevelConfig = new HashMap<Integer, ArrayList<TowerParameters>>();
	
	static private TowerParameters createParameter(int towerType, double firingSpeed, int range, double priceToBuy, double priceToSell,String immagePath, int towerCurrentLevel)
	{
		TowerParameters retVal = new TowerParameters();
		retVal.towerType = towerType; 
		retVal.firingSpeed = firingSpeed;
		retVal.range = range; // number of affected cells
		//retVal.position;
		retVal.imagePath = immagePath;
		retVal.towerCurrentLevel = towerCurrentLevel;
		retVal.salePrice = priceToSell;
		retVal.buyPrice = priceToBuy;
		
		
		
		return retVal;
	}

	public TowerParameters returnTowerParameters( int towerType){
		
		return towerLevelConfig.get(towerType).get(0);
	}

	public TowerDataCollection(){
		ArrayList<TowerParameters> firing = new ArrayList<TowerParameters>();
		
		//towerType, firingSpeed,range,priceToBuy,  priceToSell, immagePath,towerCurrentLevel
		firing.add(createParameter(10, 1, 1,10,5, "/tower1_0.png", 0));
		firing.add(createParameter(10, 2, 2, 20,10, "/tower1_1.png", 1));
		firing.add(createParameter(10, 3, 3, 30, 15, "/tower1_2.png", 2));		
		towerLevelConfig.put(10, firing);
		
		ArrayList<TowerParameters> freezing = new ArrayList<TowerParameters>();
		freezing.add(createParameter(20, 1,1,40,20, "/tower2_0.png", 0));
		freezing.add(createParameter(20, 2, 2,50,25, "/tower2_1.png", 1));
		freezing.add(createParameter(20, 3, 3,60,30, "/tower2_2.png", 2));
		
		towerLevelConfig.put(20, freezing);
		
		ArrayList<TowerParameters> basic = new ArrayList<TowerParameters>();
		basic.add(createParameter(30, 1, 1,70,35, "/tower3_0.png", 0));
		basic.add(createParameter(30, 2, 2, 80,40,"/tower3_1.png", 1));
		basic.add(createParameter(30, 2, 2, 90, 45,"/tower3.png", 2));
		
		towerLevelConfig.put(30, basic);
	}
	
}
