package core.domain.warriors.aliens;

import java.util.UUID;

import core.domain.Subject;
import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.warriors.aliens.behaviours.SoundBehaviour;


// TODO: Auto-generated Javadoc
/**
 * The Class Critter.
 */
public abstract class Critter {
	
	/** The Id. */
	public String Id= UUID.randomUUID().toString();
	
	/** The moving behaviour. */
	MovingBehaviour movingBehaviour;
	
	/** The sound behavior. */
	SoundBehaviour soundBehaviour;
	
	/**
	 * Sets the moving behaviour.
	 *
	 * @param movingBehaviour the new moving behaviour
	 */
	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	
	/**
	 * Sets the sound behaviour.
	 *
	 * @param soundBehaviour the new sound behaviour
	 */
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}
	
	/**
	 * Perform moving behaviour.
	 */
	public void performMovingBehaviour(){
		movingBehaviour.move();
	}
	
	/**
	 * Perform sound behaviour.
	 */
	public void performSoundBehaviour(){
		soundBehaviour.sound();
	}

	/** The description. */
	protected String description;
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Display.
	 */
	public abstract void display();
	
	/**
	 * Life booster.
	 *
	 * @return the double
	 */
	public abstract double lifeBooster();
}
