package core.applicationService.mapServices.connectivity.imp;

import core.applicationService.mapServices.connectivity.IStartEndChecker;
import core.domain.waves.Position;

public class StartEndChecker implements IStartEndChecker {
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IStartEndChecker#hasOverlap(core.domain.waves.Position, core.domain.waves.Position)
	 */
	@Override
	public boolean hasOverlap(Position first, Position second){
		return first.equals(second);
	}
	/* (non-Javadoc)
	 * @see core.applicationService.mapServices.connectivity.imp.IStartEndChecker#isInEdge(int, int, core.domain.waves.Position)
	 */
	@Override
	public boolean isInEdge(int width, int height, Position position){
			if(condition(width, height, position)){
				return true;
			}else
				return false;
	}
	@Override
	public boolean hasStart(int[][] matrix){
		return nodeChecker(matrix, 2);
	}
	@Override
	public boolean hasEnd(int[][] matrix) {
			return nodeChecker(matrix, 3);
	}
	public boolean condition(int width, int height, Position position){
		return ((position.getX() == 0) || (position.getX() ==width))
				|| ((position.getY() == 0) || (position.getY() == height));
	}
	public boolean nodeChecker(int[][] matrix, int expected){
		boolean flag = false;
		try {
			for (int[] rows : matrix) {
				for (int num : rows) {
					if(num == expected)
						flag = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	@Override
	public boolean isCorrectSize(int height, int width) {
		return (height < 30 && width < 30);
	}
	
}
