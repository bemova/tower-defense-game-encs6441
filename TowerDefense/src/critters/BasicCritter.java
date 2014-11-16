package critters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import maps.GridCell;
import critters.Critter.State;

public class BasicCritter  extends Observable implements Critter {
	
	protected CritterMovingStrategy movingStrategy;
	protected Critter.State state = Critter.State.INVISIBLE;
	protected int maximumHitPoints;
	protected int currentHitPoint;
	protected int demage;
	protected double speed; // number of pixels per 1 millisecond
	
	protected long appearTimeout;
	
	ArrayList<GridCell> path;
	
	Point currentPosition = null;
	public int numberOfCellsPassed = 0;
	
	Point startPoint;
	Point endPoint ;
	

	

	
	public BasicCritter() {
		speed = (15.0 / 1000); // 15 pixel per 1000 milliseconds and 15 / 1000 per on millisecond
		maximumHitPoints = 1;
		currentHitPoint = 0;
		demage = 0; // TODO move to tower
		numberOfCellsPassed = 0;

	}
	
	private Point createPoint(GridCell cell) {
		return new Point(cell.j * 30 + 15, cell.i * 30 + 15);
	}
	

	@Override
	public CritterMovingStrategy getMovingBehaviour() {
		return movingStrategy;
	}


	@Override
	public double getSpeed() {
		return speed;
	}
	
	
	@Override
	public void updatePosition(long timeMillisecs, Critter critter) {
		executeStrategy(timeMillisecs, critter);
	}
	

	@Override
	public void setAppearTime(long currentInterval) {
		appearTimeout = currentInterval;
	}
	@Override
	public double getAppearTime() {
		return appearTimeout;
	}
	@Override
	public void setPath(ArrayList<GridCell> _path) {
		path = _path;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void draw(Graphics g) {
		if(state == Critter.State.MOVING)
		{
			Color c = g.getColor();
			g.setColor(Color.white);
			g.fillOval(currentPosition.x, currentPosition.y, 15, 15);
			g.setColor(Color.black);
			g.drawString(demage + "", currentPosition.x, currentPosition.y);
			g.setColor(c);
			
		}		
	}

	@Override
	public int getHitPoints() {
		
		return maximumHitPoints;
	}	
	
	
	private void executeStrategy(long timeMillisecs, Critter critter) {

		if(state != Critter.State.COMPLETEDPATH && state != Critter.State.DEAD){
			
		if(appearTimeout <= 0)
		{
			appearTimeout += timeMillisecs;
			if(appearTimeout < 0)
			{
				return;
			}
			
			state = Critter.State.MOVING;
			timeMillisecs = appearTimeout;
		}
		
		
		if(currentPosition == null)
		{
			startPoint = createPoint(path.get(numberOfCellsPassed));
			endPoint = createPoint(path.get(numberOfCellsPassed + 1));

			currentPosition = startPoint;
		} 
		
		if((currentPosition = movingStrategy.executeMovement(timeMillisecs, critter)) == null){
			state = Critter.State.COMPLETEDPATH;
			
		}
		
		}
		
	}

	@Override
	public ArrayList<GridCell> getPath() {
		
		return path;
	}

	@Override
	public int getNumberOfCellsPassed() {
		
		return numberOfCellsPassed;
	}

	@Override
	public Point getCurrentPosition() {
		
		return currentPosition;
	}

	@Override
	public void setNumberOfCellsPassed(int newPassedCells) {

		numberOfCellsPassed = newPassedCells;
		
	}

	@Override
	public void setcurrentPosition(Point newPosition) {
		currentPosition = newPosition;
		
	}

	@Override
	public Point getStartPoint() {
		
		return startPoint;
	}

	@Override
	public Point getEnedPoint() {
		
		return endPoint;
	}

	@Override
	public void setStartPoint(Point newValue) {
		startPoint = newValue;
		
	}

	@Override
	public void setEnedPoint(Point newValue) {
		endPoint = newValue;
		
	}

	@Override
	public void setMovingStrategy(CritterMovingStrategy newMovingStrategy) {
		movingStrategy = newMovingStrategy;
		
	}




	@Override
	public int getDemageAmount() {
		
		return demage;
	}

	@Override
	public void setDemage() {
		demage ++;
		
	}

	@Override
	public void setState(State newState) {
		state = newState;
		
	}

	@Override
	public void notifyregisteredObservers() {
		setChanged();
		notifyObservers();
		
	}
	


	
}
