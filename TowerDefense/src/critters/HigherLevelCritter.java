package critters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import maps.GridCell;

public class HigherLevelCritter extends CritterDecorater{

	public HigherLevelCritter(Critter newCritter) {
		super(newCritter);
		
	}
	

	@Override
	public CritterMovingStrategy getMovingBehaviour() {
		return criterObject.getMovingBehaviour();
	}


	@Override
	public double getSpeed() {
	
		return criterObject.getSpeed() + 5.0/1000;
	}

	@Override
	public void updatePosition(long timeMillisecs, Critter critter) {
		criterObject.updatePosition(timeMillisecs, critter);
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
		
		return criterObject.getHitPoints() + 2;
	}

	@Override
	public ArrayList<GridCell> getPath() {

		return criterObject.getPath();
	}

	@Override
	public int getNumberOfCellsPassed() {
		
		return criterObject.getNumberOfCellsPassed();
	}

	@Override
	public Point getCurrentPosition() {
		
		return criterObject.getCurrentPosition();
	}

	@Override
	public Point getStartPoint() {
		
		return criterObject.getStartPoint();
	}

	@Override
	public Point getEnedPoint() {
		
		return criterObject.getEnedPoint();
	}

	@Override
	public void setNumberOfCellsPassed(int newPassedCells) {
		criterObject.setNumberOfCellsPassed(newPassedCells);
		
	}

	@Override
	public void setcurrentPosition(Point newPosition) {
		criterObject.setcurrentPosition(newPosition);
		
	}

	@Override
	public void setStartPoint(Point newValue) {
		criterObject.setStartPoint(newValue);
		
	}

	@Override
	public void setEnedPoint(Point newValue) {
		criterObject.setEnedPoint(newValue); 
		
	}

	@Override
	public void setMovingStrategy(CritterMovingStrategy newMovingStrategy) {
		criterObject.setMovingStrategy(newMovingStrategy);
		
	}


	@Override
	public int getDemageAmount() {
		
		return criterObject.getDemageAmount();
	}


	@Override
	public void setDemage() { 
		criterObject.setDemage();
		
	}


	@Override
	public void setState(State newState) {
		criterObject.setState(newState);
		
	}


	@Override
	public void notifyregisteredObservers() {
		setChanged();
		notifyObservers();
		
	}



}
