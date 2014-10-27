package core.domain.warriors.aliens.critterType;

import core.domain.warriors.aliens.Critter;

/**
 * <b>this type of critters can  decide by themself and by game strategy and AI Algorithms</b>
 * @author mojtaba
 * @version 0.1
 */
public class IntelligentCritter extends Critter {
	
	/**
	 * Instantiates a new intelligent critter.
	 */
	public IntelligentCritter() {
		this.description = "IntelligentCritter";
	}

	/**
	 * <b>Move method will be used in the second build to display critters and it is not complete yet</b>
	 */
	@Override
	public void display() {
		System.out.println("I am IntelligentCritter!");

	}

	/**
	 * <b>this method is used for bulid 2 and by this method can return life  for the tower </b>
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 7;
	}

}
