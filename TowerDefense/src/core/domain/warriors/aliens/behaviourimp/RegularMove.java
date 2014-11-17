package core.domain.warriors.aliens.behaviourimp;

import core.applicationservice.informerservices.imp.DefenderInformer;
import core.applicationservice.locationservices.PositionService;
import core.applicationservice.warriorservices.ShootingService;
import core.contract.MapConstants;
import core.domain.warriors.aliens.behaviours.MovingBehaviour;
import core.domain.waves.Position;

/**
 * <b>this is used for strategy design pattern this class is defined to
 * implement the one of the moving behavior of a alien that is a kind of regular
 * move </b>
 * 
 * @author Team5
 * @version 0.1
 */
public class RegularMove implements MovingBehaviour {

	private Position[] path;
	private int xStep;
	private int yStep;
	private Position PixelPosition;
	private int currentPosition;
	private int freezeTime;

	public RegularMove(Position[] path, Position initialPixel) {
		this.PixelPosition = initialPixel;
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	/**
	 * <b>this sound method not implemented now but we make the skeleton of the
	 * class for the second build</b>
	 */
	@Override
	public void move() {
		if (freezeTime <= 0) {
			int xPixel = PixelPosition.getX();
			int yPixel = PixelPosition.getY();
			int currPathCell = currentPosition;
			if (currPathCell != path.length-1) {
				
				Position currCell = path[currPathCell];
				Position nextCell = path[currPathCell + 1];

				if (currCell.getY() == nextCell.getY()) {
					if (currCell.getX() + 1 == nextCell.getX()) {
						xPixel += 1;
						xStep += 1;
						if (xStep >= MapConstants.UNIT_SIZE - 1) {
							xStep = 0;
							currPathCell++;
						}

					} else if (currCell.getX() - 1 == nextCell.getX()) {
						xPixel -= 1;
						xStep += 1;
						if (xStep >= MapConstants.UNIT_SIZE - 1) {
							xStep = 0;
							currPathCell++;
						}
					}

				}
				if (currCell.getX() == nextCell.getX()) {
					if (currCell.getY() + 1 == nextCell.getY()) {
						yPixel += 1;
						yStep += 1;

					} else if (currCell.getY() - 1 == nextCell.getY()) {
						yPixel -= 1;
						yStep += 1;
					}
					if (yStep >= MapConstants.UNIT_SIZE - 1) {
						yStep = 0;
						currPathCell++;
					}
				}
			}
			this.PixelPosition = new Position(xPixel, yPixel);
			currentPosition = currPathCell;
		} else {
			if (freezeTime > 0) {
				freezeTime--;
			}
		}
	}

	
	public Position getPixelPosition() {
		return PixelPosition;
	}


	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public Position[] getPath(){
		return path;
	}
	public void setFreezeTime(int freezeTime) {
		this.freezeTime = freezeTime;
	}

}
