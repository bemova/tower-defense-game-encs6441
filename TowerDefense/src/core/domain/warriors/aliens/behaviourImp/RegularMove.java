package core.domain.warriors.aliens.behaviourImp;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;
/**
 * <b>this is used for strategy design pattern
 * this class is defined to implement the one of the moving behavior of a alien
 * that is a kind of regular move
 * </b>
 * @author Team5
 *@version 0.1
 */
public class RegularMove implements MovingBehaviour{

	/**
	 * <b>this sound method not implemented now but we make the skeleton of the class for the second build</b>
	 */
	@Override
	public void move() {
		System.out.println(" i have simple moving or dummy moving");
		
	}

}
