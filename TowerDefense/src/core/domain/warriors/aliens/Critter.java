package core.domain.warriors.aliens;

import java.util.UUID;

import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.warriors.aliens.behaviours.SoundBehaviour;
import core.domain.waves.Position;


/**
 * this class is the abstract class that has all information about critters and it is ready to 
 * use in decorated critters and for having different critters by different behaviors(Strategy Design pattern)
 * @author Team5
 * @version 0.1
 */
public abstract class Critter {
	
	/** The Id. */
	public String Id= UUID.randomUUID().toString();
	private int currentPosition;
	private Position[] path;
	private Position initialPixel;

	
	/** The moving behaviour. */
	MovingBehaviour movingBehaviour;
	
	/** The sound behaviour. */
	SoundBehaviour soundBehaviour;
	
	private int life;
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	
	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * Sets the moving behaviour.
	 *
	 * @param movingBehaviour the new moving behaviour
	 */
	public void setMovingBehaviour(MovingBehaviour movingBehaviour) {
		this.movingBehaviour = movingBehaviour;
	}
	
	public MovingBehaviour getMovingBehaviour() {
		return this.movingBehaviour;
	}
	
	/**
	 * Sets the sound behaviour.
	 *
	 * @param soundBehaviour the new sound behaviour
	 */
	public void setSoundBehaviour(SoundBehaviour soundBehaviour) {
		this.soundBehaviour = soundBehaviour;
	}
	

	
	public Position getInitialPixel() {
		return initialPixel;
	}

	public void setInitialPixel(Position initialPixel) {
		this.initialPixel = initialPixel;
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
	
	
	public Position[] getPath() {
		return path;
	}

	public void setPath(Position[] path) {
		this.path = path;
	}

	/**
	 * Display.
	 */
	public abstract String display();
	
	/**
	 * Life booster.
	 *
	 * @return the double
	 */
	public abstract double lifeBooster();
}
