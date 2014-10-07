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
			if(((position.getX() == 0) || (position.getX() ==width))
					|| ((position.getY() == 0) || (position.getY() == height))){
				return true;
			}else
				return false;
	}
}
