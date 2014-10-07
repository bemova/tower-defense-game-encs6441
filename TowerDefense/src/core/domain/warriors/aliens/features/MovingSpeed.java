package core.domain.warriors.aliens.features;

import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.CritterFeatureDecorator;

public class MovingSpeed extends CritterFeatureDecorator {

	private Critter critter;
	public MovingSpeed(Critter critter) {
		this.critter = critter;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.critter.getDescription()+",MovingSpeed";
	}
	@Override
	public void display() {
		this.critter.display();
	}

	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 30 + this.critter.lifeBooster();
	}

}
