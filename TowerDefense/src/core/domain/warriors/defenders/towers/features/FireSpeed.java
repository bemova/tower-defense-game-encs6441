package core.domain.warriors.defenders.towers.features;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
/**
 * <b>this class used to implement decorator pattern for Towers
 * that is one of the feature that are used for towers</b>
 * @author mojtaba
 * @version 0.1
 */
public class FireSpeed extends TowerFeatureDecorator {
	private Tower tower;
	
	public FireSpeed(Tower tower) {
		this.tower = tower;
		// TODO Auto-generated constructor stub
	}

	/**
	 * in this method we will use to get decorated object description
	 * @return description of tower as String that contains all features and the tower type
	 */
	@Override
	public String getDescription() {
		return this.tower.getDescription()+",fireSpeed";
	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower</b>
	 * <code>
	 *  return DefenderConstants.FIRE_SPEED + this.tower.cost();
	 * </code>
	 */
	@Override
	public long cost() {
		// TODO Auto-generated method stub
		return DefenderConstants.FIRE_SPEED + this.tower.cost();
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that is representative of a tower
	 */
	@Override
	public Color display() {
		return this.tower.display();
		
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
