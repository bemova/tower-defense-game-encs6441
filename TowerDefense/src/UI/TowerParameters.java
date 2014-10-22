package UI;

import java.awt.Point;

public class TowerParameters {
	
	public int towerType ; 
	public double firingSpeed;
	public int range = 1; // number of affected cells
	public Point position;
	public String imagePath;
	public int towerCurrentLevel;
	public String immagePath;
	public double buyPrice;
	public double salePrice;
	
	/*
	 * This function is called when the level of tower is changed
	 * so all the values of parameter are assigned except position
	 */
	public void update(TowerParameters newValues)
	{
		towerType = newValues.towerType; 
		firingSpeed = newValues.firingSpeed;
		range  = newValues.range; // number of affected cells
		//Point position;
		imagePath = newValues.imagePath;
		towerCurrentLevel = newValues.towerCurrentLevel;
	}

}
