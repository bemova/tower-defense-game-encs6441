package core.domain.warriors.aliens.behaviourImp;

import core.domain.warriors.aliens.behaviours.SoundBehaviour;

public class DyingWailSound implements SoundBehaviour {

	/* (non-Javadoc)
	 * @see core.domain.warriors.aliens.behaviours.SoundBehaviour#sound()
	 */
	@Override
	public void sound() {
		System.out.println("Ouch!");
	}
}
