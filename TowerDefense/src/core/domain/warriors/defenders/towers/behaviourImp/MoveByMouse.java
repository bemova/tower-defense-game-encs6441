package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;



public class MoveByMouse implements MovingBehaviour {

	/* (non-Javadoc)
	 * @see core.domain.warriors.defenders.towers.behaviours.MovingBehaviour#move()
	 */
	@Override
	public void move() {
		System.out.println("I can move by mouse!");
		
	}

}
