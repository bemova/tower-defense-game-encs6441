package core.domain.warriors.aliens.behaviourImp;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;

public class MoveByStrategy implements MovingBehaviour {

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.behaviours.MovingBehaviour#move()
	 */
	@Override
	public void move() {
		System.out.println("I can move by game's strategy!");

	}

}
