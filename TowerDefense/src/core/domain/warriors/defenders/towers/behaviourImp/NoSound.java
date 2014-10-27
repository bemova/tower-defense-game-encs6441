package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
/**
 * <h4> this is used for strategy design pattern</h4>
 * <b>this class is defined to implement the one of the sound behavior of a tower</b>
 * that is a kind of no sound</b>
 * @author mojtaba
 *@version 0.1
 */
public class NoSound implements SoundBehaviour {
	/**
	 * <b>this sound method not implemented now but we make the skeleton of the class for the second build</b>
	 */ 
	@Override
	public void sound() {
		System.out.println("I don't have any sound!");

	}

}
