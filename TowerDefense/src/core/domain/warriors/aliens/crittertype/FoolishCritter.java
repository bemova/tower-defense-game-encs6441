package core.domain.warriors.aliens.crittertype;

import core.domain.warriors.aliens.Critter;

/**
 * <b>this type of critters can not decide by themself and by game strategy and AI Algorithms</b>
 * @author Team5
 * @version 0.1
 */
public class FoolishCritter extends Critter {
	
	/**
	 * Instantiates a new foolish critter.
	 */
	public FoolishCritter() {
		this.description = "FoolishCritter";
	}

	/**
	 * <b>Move method will be used in the second build to display critters and it is not complete yet</b>
	 */
	@Override
	public void display() {
		System.out.println("I am FoolishCritter!");
		
	}

	/**
	 * <b>this method is used for bulid 2 and by this method can return life  for the tower </b>
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 5;
	}

}
