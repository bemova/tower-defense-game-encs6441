package core.domain.warriors.defenders.towers.towerType;


import java.awt.Color;

import core.contract.DefenderConstatns;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourImp.NoMove;
import core.domain.warriors.defenders.towers.behaviourImp.NoSound;
import core.domain.warriors.defenders.towers.behaviourImp.ShootTrap;

public class AncientTower extends Tower {
	public AncientTower() {
		setMovingBehaviour(new NoMove());
		setShootingBehaviour(new ShootTrap());
		setSoundBehaviour(new NoSound());
		//this.description = "Ancient Tower";
	}

	@Override
	public Color display() {
		return MapConstants.ANCIENT_TOWER_COLOR;

	}
	@Override
	public long cost() {
		// TODO Auto-generated method stub
		return DefenderConstatns.ANCIENT_TOWER;
	}

	
}


