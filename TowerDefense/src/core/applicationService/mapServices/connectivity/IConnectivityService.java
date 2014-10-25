package core.applicationService.mapServices.connectivity;

import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public interface IConnectivityService {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public boolean isTherePath(Position start, Position end, GridCellContentType[][] matrix);

}