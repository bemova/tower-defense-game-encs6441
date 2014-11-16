package UI;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.omg.Dynamic.Parameter;

import Towers.BasicTower;
import Towers.FireShooting;
import Towers.IceShooting;
import Towers.TowerDataCollection;
import Towers.TowerInterface;

public class FactoryTower {
	TowerDataCollection towerData = new TowerDataCollection();

	/*
	 * public Tower creatTower(){
	 * 
	 * return new AncientTower(); }
	 */

	//
/*	public Tower creatTower(TowerParameters newParameters) {
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
	}; */
	
	// 10 => fireShooting, 20 => IceShooting
	
	public TowerInterface creatTower(int towerType) {
		TowerInterface obj = null;

		obj = new BasicTower();
		if (towerType == 10) {
			obj.SetShootingStrategy(new FireShooting());
			((BasicTower)obj).imagePath = "/tower1_0.png";
			
		} else if (towerType == 20) {
			obj.SetShootingStrategy(new IceShooting());
			((BasicTower)obj).imagePath = "/tower2_0.png";
		} else if (towerType == 30) {
			obj.SetShootingStrategy(new IceShooting());
			((BasicTower)obj).imagePath = "/tower3_0.png";
		} 

		try {
			((BasicTower)obj).image = ImageIO.read(this.getClass().getResource(
					((BasicTower)obj).imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return obj;
	};
	

	public void updateTowerParameters() {

	}

}
