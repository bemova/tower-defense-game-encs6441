package core.domain.warriors.aliens;

import java.util.UUID;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.warriors.aliens.behaviours.SoundBehaviour;


/**
 * this class is the abstract class that has all information about critters and it is ready to 
 * use in decorated critters and for having different critters by different behaviors(Strategy Design pattern)
 * @author mojtaba
 * @version 0.1
 */
public abstract class Critter {
	
	/** The Id. */
	public String Id= UUID.randomUUID().toString();
	
	/** The moving behaviour. */
	MovingBehaviour movingBehaviour;
	
	/** The sound behaviour. */
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
