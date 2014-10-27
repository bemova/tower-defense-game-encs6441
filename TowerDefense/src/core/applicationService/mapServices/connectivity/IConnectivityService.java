package core.applicationService.mapServices.connectivity;

import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

public interface IConnectivityService {

	/**
	 * this interface was implemented only for dependency injection that will be implemented in the future
	 * this sigature use for the class that can validate if there is nay path in our grid or not
	 * @param start
	 * @param end
	 * @param matrix
	 * @return boolean
	 */
	public boolean isTherePath(Position start, Position end, GridCellContentType[][] matrix);

}