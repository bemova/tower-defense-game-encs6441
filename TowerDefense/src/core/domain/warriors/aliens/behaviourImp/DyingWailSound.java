package core.domain.warriors.aliens.behaviourImp;

import core.domain.warriors.aliens.behaviours.SoundBehaviour;
/**
 * <h4> this is used for strategy design pattern</h4>
 * <b>this class is defined to implement the one of the sound behavior of a tower</b>
 * that is a kind of dying wail sound</b>
 * @author mojtaba
 *@version 0.1
 */
public class DyingWailSound implements SoundBehaviour {

	/**
	 * this sound method not implemented now but we make the skeleton of the class for the second build
	 */
	@Override
	public void sound() {
		System.out.println("Ouch!");
	}
}
