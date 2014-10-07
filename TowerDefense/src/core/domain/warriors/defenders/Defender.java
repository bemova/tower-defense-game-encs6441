package core.domain.warriors.defenders;

import core.applicationService.informerServices.Observer;
import core.domain.Subject;
import core.domain.waves.Position;

public abstract class Defender implements Observer {
	protected Subject subject;
	protected Position waveHeadPosition;
	public void update(Position waveHeadPosition) {
		this.waveHeadPosition = waveHeadPosition;
	}

}
