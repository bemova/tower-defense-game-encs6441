package core.applicationService.mapServices.connectivity;

import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

// TODO: Auto-generated Javadoc
/**
 * The Interface IStartEndChecker.
 */
public interface IStartEndChecker {

	/**
	 * Checks for overlap.
	 *
	 * @param first the first
	 * @param second the second
	 * @return true, if successful
	 */
	public boolean hasOverlap(Position first, Position second);

	/**
	 * Checks if is in edge.
	 *
	 * @param width the width
	 * @param height the height
	 * @param position the position
	 * @return true, if is in edge
	 */
	public boolean isInEdge(int width, int height, Position position);
	
	public boolean hasStart(GridCellContentType[][] matrix);
	public boolean hasEnd(GridCellContentType[][] matrix);
	public boolean isCorrectSize(int height, int width);

}