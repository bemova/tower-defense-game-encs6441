package core.domain.warriors.defenders.towers.towerType;


import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.behaviourImp.NoMove;
import core.domain.warriors.defenders.towers.behaviourImp.NoSound;
import core.domain.warriors.defenders.towers.behaviourImp.ShootTrap;
import core.domain.warriors.defenders.towers.vikiTowers.TowerParameters;

public class AncientTower extends Tower {
	public AncientTower() {
		setMovingBehaviour(new NoMove());
		setShootingBehaviour(new ShootTrap());
		setSoundBehaviour(new NoSound());
		//this.description = "Ancient Tower";
	}

	@Override
	public void display() {
		System.out.println("I am Anceint!");

	}
	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 2;
	}
	

		
	}


