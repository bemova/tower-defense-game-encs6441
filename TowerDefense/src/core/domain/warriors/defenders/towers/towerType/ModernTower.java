package core.domain.warriors.defenders.towers.towerType;


import java.awt.Color;

import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourImp.MoveByMouse;
import core.domain.warriors.defenders.towers.behaviourImp.ShootWithWeapon;
import core.domain.warriors.defenders.towers.behaviourImp.ShootingSound;
/**
 * <b>this type of tower has ShootWithWeapon as a weapon and it has MoveByMouse & ShootingSound </b>
 * @author mojtaba
 * @version0.1
 */
public class ModernTower extends Tower {

	public ModernTower() {
		setMovingBehaviour(new MoveByMouse());
		setShootingBehaviour(new ShootWithWeapon());
		setSoundBehaviour(new ShootingSound());
		this.description = "ModernTower";
	}
	/**
	 * 
	 * it will be show the appearance of the tower until now we used for color
	 * @return Color, that is color of the button that is representative of a tower
	 */
	@Override
	public Color display() {
		return MapConstants.MODERN_TOWER_COLOR;

	}
	/**
	 * 
	 * <b>by this method we can calculate the cost of decorated tower type that is a base tower</b>
	 * <code>
	 *  return DefenderConstants.MODERN_TOWER;
	 * </code>
	 */
	@Override
	public long cost() {
		return DefenderConstants.MODERN_TOWER;
	}

}
