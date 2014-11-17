package core.domain.warriors.defenders;

import java.util.Observable;

import core.applicationservice.informerservices.Observer;
import core.domain.Subject;
import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * <b>this class is imply observer design patter for informing all towers about the critters'position </b>
 * @author Team5
 * @version 0.1
 */
public abstract class Defender extends Observable {
	protected Subject subject;
	/**
	 * it is a head position of the wave
	 */
	protected Position waveHeadPosition;
	protected Position alienPosition;
	/**
	 * it is implemented for having observer design pattern
	 * @param waveHeadPosition the head position of the wave
	 */
	public void waveUpdate(Position waveHeadPosition) {
		this.waveHeadPosition = waveHeadPosition;
	}
	

}
