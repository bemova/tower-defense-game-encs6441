package core.applicationService.informerServices;

import core.domain.waves.Position;

/**
 * The Interface Observer.
 * we need this interface for implementing of observer design pattern from the scratch
 */
public interface Observer {
	
	/**
	 *
	 * @param position  as Position that represent the position of the wave's head
	 */
	public void update(Position position);

}
