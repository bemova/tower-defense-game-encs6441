package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;



public class NoSound implements SoundBehaviour {

	@Override
	public void sound() {
		System.out.println("I don't have any sound!");

	}

}
