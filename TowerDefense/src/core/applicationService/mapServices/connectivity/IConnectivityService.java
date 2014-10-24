package core.applicationService.mapServices.connectivity;

import core.domain.waves.Position;

public interface IConnectivityService {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public boolean isTherePath(Position start, Position end, int[][] matrix);

}