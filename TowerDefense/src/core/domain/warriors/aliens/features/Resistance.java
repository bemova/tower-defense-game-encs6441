package core.domain.warriors.aliens.features;

import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.CritterFeatureDecorator;

public class Resistance extends CritterFeatureDecorator {
	private Critter critter;
	public Resistance(Critter critter) {
		this.critter = critter;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.critter.getDescription()+",resistance";
	}
	@Override
	public void display() {
		this.critter.display();
	}

	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 10 + this.critter.lifeBooster();
	}

}
