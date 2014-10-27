package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;


/**
 * <h4> this is used for strategy design pattern</h4>
 *<b>this class is defined to implement the one of the moving behavior of a tower</b>
 * that is a kind of move by strategy</b>
 * @author mojtaba
 * @version 0.1
 */
public class MoveByMouse implements MovingBehaviour {

	/**
	 * moving action of a tower during the game on the grid this tower should to able by mouse on the grid
	 */
	@Override
	public void move() {
		System.out.println("I can move by mouse!");
		
	}

}
