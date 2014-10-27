package core.domain.warriors.defenders.towers.behaviourImp;

import core.domain.warriors.defenders.towers.behaviours.SoundBehaviour;
/**
 * <h4> this is used for strategy design pattern</h4>
 * <b>this class is defined to implement the one of the shooting sound behavior of a tower</b>
 * that is a kind of shooting sound</b>
 * @author mojtaba
 *@version 0.1
 */
public class ShootingSound implements SoundBehaviour {
	/**
	 * this sound should to play during shooting by a tower
	 */
	@Override
	public void sound() {
		System.out.println("I have shooting sound!");

	}

}
