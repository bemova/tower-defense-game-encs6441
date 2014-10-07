package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;



public class MoveByStrategy implements MovingBehaviour {

	@Override
	public void move() {
		System.out.println("I can move by game's strategy!");

	}

}
