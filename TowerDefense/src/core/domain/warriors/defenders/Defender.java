package core.domain.warriors.defenders;

import core.applicationservice.informerservices.Observer;
import core.domain.Subject;
import core.domain.waves.Position;

/**
 * <b>this class is imply observer design patter for informing all towers about the critters'position </b>
 * @author Team5
 * @version 0.1
 */
public abstract class Defender implements Observer {
	protected Subject subject;
	/**
	 * it is a head position of the wave
	 */
	protected Position waveHeadPosition;
	protected Position alienPosition;
	/**
	 * it is implemented for having observer design pattern
	 */
	public void waveUpdate(Position waveHeadPosition) {
		this.waveHeadPosition = waveHeadPosition;
	}
	/**
	 * it is implemented for having observer design pattern
	 */
	public abstract void alienUpdate(Position alienPosition, String CritterId);
	

}
