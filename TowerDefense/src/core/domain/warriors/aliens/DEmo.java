package core.domain.warriors.aliens;

import core.domain.warriors.aliens.critterType.FoolishCritter;
import core.domain.warriors.aliens.features.MovingSpeed;
import core.domain.warriors.aliens.features.Resistance;

public class DEmo {
	public static void main(String[] args) {
		FoolishCritter critter = new FoolishCritter();
		Critter specialCritter = new MovingSpeed(critter);
		System.out.println(specialCritter.lifeBooster());
		specialCritter = new Resistance(specialCritter);
		System.out.println(specialCritter.getDescription());
	}
}
