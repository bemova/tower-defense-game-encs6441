package core.domain.warriors.defenders.towers.features;

import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;

public class FireSpeed extends TowerFeatureDecorator {
	private Tower tower;
	public FireSpeed(Tower tower) {
		this.tower = tower;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.tower.getDescription()+",fireSpeed";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 5 + this.tower.cost();
	}

	@Override
	public void display() {
		this.tower.display();
		
	}

}
