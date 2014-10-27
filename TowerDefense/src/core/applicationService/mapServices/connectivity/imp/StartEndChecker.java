package core.applicationService.mapServices.connectivity.imp;

import core.applicationService.mapServices.connectivity.IStartEndChecker;
import core.domain.maps.GridCellContentType;
import core.domain.waves.Position;

/**
 * this calss used as a service to chech some validation in map based on the position of start and end 
 * @author mojtaba , ali
 * @version 0.1
 */
public class StartEndChecker implements IStartEndChecker {
	/**
	 * <b>it can used for check any two positions that were selected have overlap with others our not</b>
	 * @param first 
	 * @param second
	 * @return boolean
	 */
	@Override
	public boolean hasOverlap(Position first, Position second){
		return first.equals(second);
	}
	/**
	 * <b>it can check if the node that was selected is located on the age of grid or not</b>
	 * @param width
	 * @param height
	 * @param position
	 * @return
	 */
	@Override
	public boolean isInEdge(int width, int height, Position position){
			if(condition(width, height, position)){
				return true;
			}else
				return false;
	}
	/**
	 * <b>it can check if there is any start point on grid or not</b>
	 * @param matrix
	 * @return boolean
	 */
	@Override
	public boolean hasStart(GridCellContentType[][] matrix){
		return nodeChecker(matrix, GridCellContentType.ENTRANCE);
	}
	/**
	 * <b>
	 * it can check if there is any start point on grid or not
	 * </b>
	 * @param matrix
	 * @return boolean
	 */
	@Override
	public boolean hasEnd(GridCellContentType[][] matrix) {
			return nodeChecker(matrix, GridCellContentType.EXIT);
	}
	/**
	 * <b>it defined as a method extract refactoring for condition </b>
	 * @param width
	 * @param height
	 * @param position
	 * @return
	 */
	private boolean condition(int width, int height, Position position){
		return ((position.getX() == 0) || (position.getX() ==width))
				|| ((position.getY() == 0) || (position.getY() == height));
	}
	/**
	 * <b>it defined as a method extract refactoring </b>
	 * @param matrix
	 * @param expected
	 * @return
	 */
	public boolean nodeChecker(GridCellContentType[][] matrix, GridCellContentType expected){
		boolean flag = false;
		try {
			for (GridCellContentType[] rows : matrix) {
				for (GridCellContentType type : rows) {
					if(type == expected)
						flag = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	/**
	 * <b>grid size validation </b>
	 * <code>((height >= 5 && height <= 30) && (width >= 5 && width <= 30))</code>
	 * @param height
	 * @param width
	 * @return boolean
	 */
	@Override
	public boolean isCorrectSize(int height, int width) {
		return ((height >= 5 && height <= 30) && (width >= 5 && width <= 30));
	}
	
}
