package core.domain.warriors.aliens.behaviourimp;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;

public class SimpleMoving implements MovingBehaviour{

	@Override
	public void move() {
		System.out.println("Simple");
		
	}

}
