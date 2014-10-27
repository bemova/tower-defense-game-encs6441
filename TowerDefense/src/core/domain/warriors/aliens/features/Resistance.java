package core.domain.warriors.aliens.features;

import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.CritterFeatureDecorator;
/**
 * <h4>this class used to implement decorator pattern for Critter<h4>
 * <b>that is one of the feature that are used for towers</b>
 * @author mojtaba
 * @version 0.1
 */
public class Resistance extends CritterFeatureDecorator {
	private Critter critter;
	public Resistance(Critter critter) {
		this.critter = critter;
	}
	/**
	 * in this method we will use to get decorated object description
	 * @return description of critter as String that contains all features and the critter type
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.critter.getDescription()+",resistance";
	}
	/**
	 * in this method we will use to build 2 
	 * it will be show the appearance of the critter
	 */
	@Override
	public void display() {
		this.critter.display();
	}
	/**
	 * in this method we will use to build 2 
	 * if one tower kill this decorated critter it can be get some more life, but if it is decorated 
	 * critter it will takes more life based on the feature count that critter has.
	 */
	@Override
	public double lifeBooster() {
		// TODO Auto-generated method stub
		return 10 + this.critter.lifeBooster();
	}

}
