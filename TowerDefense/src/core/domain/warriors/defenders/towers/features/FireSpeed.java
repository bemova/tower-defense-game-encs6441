package core.domain.warriors.defenders.towers.features;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import core.contract.DefenderConstants;
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
		return DefenderConstants.FIRE_SPEED + this.tower.cost();
	}

	@Override
	public Color display() {
		return this.tower.display();
		
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
