package critters;

import java.awt.Graphics;
import java.util.ArrayList;

import core.domain.maps.GridCell;

public class HigherLevelCritter extends CritterDecorater{

	public HigherLevelCritter(Critter newCritter) {
		super(newCritter);
		
	}
	
	@Override
	public void setMovingBehaviour(CritterMovingStrategy newBehaviour) {
		criterObject.setMovingBehaviour(newBehaviour);
	}
	@Override
	public CritterMovingStrategy getMovingBehaviour() {
		return criterObject.getMovingBehaviour();
	}
	@Override
	public void setSpeed(double newspeed) {
		criterObject.setSpeed(newspeed);
		
	}
	@Override
	public double getSpeed() {
	
		return criterObject.getSpeed() ;
	}

	@Override
	public void updatePosition(long timeMillisecs) {
		criterObject.updatePosition(timeMillisecs);
	}

	@Override
	public void setAppearTime(long currentInterval) {
		criterObject.setAppearTime(currentInterval);		
	}

	@Override
	public double getAppearTime() {
		return criterObject.getAppearTime();
	}

	@Override
	public void setPath(ArrayList<GridCell> path) {
		criterObject.setPath(path);		
	}

	@Override
	public State getState() {
		return criterObject.getState();
	}

	@Override
	public void draw(Graphics g) {
		criterObject.draw(g);
	}

	@Override
	public int getHitPoints() {
		
		return criterObject.getHitPoints() + 3;
	}
}
