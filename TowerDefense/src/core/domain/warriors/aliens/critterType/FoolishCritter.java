package core.domain.warriors.aliens.critterType;

import core.domain.warriors.aliens.Critter;

// TODO: Auto-generated Javadoc
/**
 * The Class FoolishCritter.
 */
public class FoolishCritter extends Critter {
	
	/**
	 * Instantiates a new foolish critter.
	 */
	public FoolishCritter() {
		this.description = "FoolishCritter";
	}

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.Critter#display()
	 */
	@Override
	public void display() {
		System.out.println("I am FoolishCritter!");
		
	}

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.Critter#lifeBooster()
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 5;
	}

}
