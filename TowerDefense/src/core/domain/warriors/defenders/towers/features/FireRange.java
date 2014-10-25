package core.domain.warriors.defenders.towers.features;

import java.util.ArrayList;
import java.util.List;

import core.contract.DefenderConstatns;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;


public class FireRange extends TowerFeatureDecorator {

	private Tower tower;
	public FireRange(Tower tower) {
		this.tower = tower;
		
	}
	@Override
	public String getDescription() {
		return this.tower.getDescription() + ",FireRange";
		
	}

	@Override
	public void display() {
		this.tower.display();
	}

	@Override
	public long cost() {
		// TODO Auto-generated method stub
		return DefenderConstatns.FIRE_RANGE + this.tower.cost();
	}

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
