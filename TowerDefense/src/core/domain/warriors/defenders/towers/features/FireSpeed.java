package core.domain.warriors.defenders.towers.features;

import java.util.ArrayList;
import java.util.List;

import core.contract.DefenderConstatns;
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
	public long cost() {
		// TODO Auto-generated method stub
		return DefenderConstatns.FIRE_SPEED + this.tower.cost();
	}

	@Override
	public void display() {
		this.tower.display();
		
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
