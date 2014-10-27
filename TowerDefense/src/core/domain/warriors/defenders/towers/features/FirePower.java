package core.domain.warriors.defenders.towers.features;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
/**
 * <h4>this class used to implement decorator pattern for Towers<h4>
 * <b>that is one of the feature that are used for towers</b>
 * @author mojtaba
 *@version 0.1
 */
public class FirePower extends TowerFeatureDecorator {

	private Tower tower;
	public FirePower(Tower tower) {
		this.tower = tower;
	}
	/**
	 * in this method we will use to get decorated object description
	 * @return description of tower as String that contains all features and the tower type
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.tower.getDescription() + ",FirePower";
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that can  representative of a feature
	 */
	@Override
	public Color display() {
		return this.tower.display();
	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower</b>
	 * <code>
	 *  return DefenderConstants.FIRE_POWER + this.tower.cost();
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.FIRE_POWER + this.tower.cost();
	}
	/**
	 * <b>this method can get a precise details about the contributer object to create a decorated tower</b>
	 */
	@Override
	public List<Tower> objectDetials() {
		List<Tower> details = new ArrayList<Tower>();
		try {
			details.addAll(tower.objectDetials());
			details.add(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return details;
	}
	
}
