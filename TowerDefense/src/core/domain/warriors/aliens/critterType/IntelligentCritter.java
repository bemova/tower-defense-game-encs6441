package core.domain.warriors.aliens.critterType;

import core.domain.warriors.aliens.Critter;

// TODO: Auto-generated Javadoc
/**
 * The Class IntelligentCritter.
 */
public class IntelligentCritter extends Critter {
	
	/**
	 * Instantiates a new intelligent critter.
	 */
	public IntelligentCritter() {
		this.description = "IntelligentCritter";
	}

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.Critter#display()
	 */
	@Override
	public void display() {
		System.out.println("I am IntelligentCritter!");

	}

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.Critter#lifeBooster()
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 7;
	}

}
