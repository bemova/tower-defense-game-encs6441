package core.domain.warriors.defenders.towers.towerType;


import java.awt.Color;

import core.contract.DefenderConstatns;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourImp.MoveByMouse;
import core.domain.warriors.defenders.towers.behaviourImp.ShootWithWeapon;
import core.domain.warriors.defenders.towers.behaviourImp.ShootingSound;

public class ModernTower extends Tower {

	public ModernTower() {
		setMovingBehaviour(new MoveByMouse());
		setShootingBehaviour(new ShootWithWeapon());
		setSoundBehaviour(new ShootingSound());
		this.description = "ModernTower";
		//this.map.put(arg0, arg1)
		
	}
	
	@Override
	public Color display() {
		return MapConstants.MODERN_TOWER_COLOR;

	}
	
	@Override
	public long cost() {
		// TODO Auto-generated method stub
		return DefenderConstatns.MODERN_TOWER;
	}

}
