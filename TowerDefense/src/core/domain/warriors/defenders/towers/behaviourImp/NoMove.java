package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.MovingBehaviour;


public class NoMove implements MovingBehaviour {

	@Override
	public void move() {
		System.out.println("I can not move!");

	}

}
