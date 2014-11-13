package critters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import core.domain.maps.GridCell;
import critters.Critter.State;

public class BasicCritter extends Observable implements Critter {
	
	protected CritterMovingStrategy movement;
	protected Critter.State state = Critter.State.INVISIBLE;
	protected int maximumHitPoints;
	protected int currentHitPoint;
	protected int demageHitPoint;
	protected double speed; // number of pixels per 1 millisecond
	protected long appearTimeout;
	
	ArrayList<GridCell> path;
	
	Point currentPosition = null;
	Point startPoint = null;
	Point endPoint = null;
	public int numberOfCellsPassed = 0;
	
	public BasicCritter() {
		speed = (15.0 / 1000); // 15 pixel per 1000 milliseconds and 15 / 1000 per on millisecond
		maximumHitPoints = 2;
		currentHitPoint = 0;
		demageHitPoint = 1; // TODO move to tower
		
	}
	
	@Override
	public void setMovingBehaviour(CritterMovingStrategy newBehaviour) {
		movement = newBehaviour;
	}
	@Override
	public CritterMovingStrategy getMovingBehaviour() {
		return movement;
	}
	@Override
	public void setSpeed(double newspeed) {
		speed = newspeed;
	}
	@Override
	public double getSpeed() {
		return speed;
	}
	
	
	@Override
	public void updatePosition(long timeMillisecs) {
		if(state == Critter.State.COMPLETEDPATH)
		{
			return;
		}
		
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
			if(path.get(numberOfCellsPassed).containsBullet){
				maximumHitPoints = maximumHitPoints -1;
				path.get(numberOfCellsPassed).containsBullet = false;
			}
				
			currentPosition = startPoint;
		}
		
		int pixelsPassed = (int)(speed * timeMillisecs);
		
		currentPosition = calculate(pixelsPassed);
		
		if(currentPosition == null)
		{
			// System.out.println("COMPLETED!!");
			state = Critter.State.COMPLETEDPATH;
		//	setChanged();
		//	notifyObservers();
		}

	}
	

	private Point createPoint(GridCell cell) {
		return new Point(cell.j * 30 + 15, cell.i * 30 + 15);
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
			g.setColor(c);
		}		
	}

	@Override
	public int getHitPoints() {
		
		return maximumHitPoints;
	}	
	
	
	
	
	private Point calculate(int pixelsPassed) {
		Point returnPoint = null;


		if(startPoint.x > endPoint.x)
		{
			if(startPoint.x - currentPosition.x - pixelsPassed >=15){
				
				path.get(numberOfCellsPassed + 1).changeState(true);
				path.get(numberOfCellsPassed).changeState(false);
			}		

			if(currentPosition.x - pixelsPassed <= endPoint.x)
			{
				int extraPixels = endPoint.x - (currentPosition.x - pixelsPassed);  
				nextCell();
				returnPoint = calculate(extraPixels);

			}else 	returnPoint = new Point(currentPosition.x - pixelsPassed, currentPosition.y);
	
		}
		else if(startPoint.x < endPoint.x)
		{
			if(currentPosition.x - startPoint.x   + pixelsPassed >=15){
				
				path.get(numberOfCellsPassed + 1).changeState(true);
				path.get(numberOfCellsPassed).changeState(false);
			}

			if(currentPosition.x + pixelsPassed >= endPoint.x)
			{
				int extraPixels = currentPosition.x + pixelsPassed - endPoint.x; 
				nextCell();
				returnPoint = calculate(extraPixels);

			}else returnPoint = new Point(currentPosition.x + pixelsPassed, currentPosition.y);

		}
		else if(startPoint.y > endPoint.y)
		{
			if(startPoint.y - currentPosition.y - pixelsPassed >=15){
				
				path.get(numberOfCellsPassed + 1).changeState(true);
				path.get(numberOfCellsPassed).changeState(false);
			}

			if(currentPosition.y - pixelsPassed <= endPoint.y)
			{
				int extraPixels = endPoint.y - (currentPosition.y - pixelsPassed);
				nextCell();
				returnPoint = calculate(extraPixels);

			}else 	returnPoint = new Point(currentPosition.x, currentPosition.y - pixelsPassed);

		}
		else if(startPoint.y < endPoint.y)
			
		{
			if(currentPosition.y - startPoint.y   + pixelsPassed >=15){
				
				path.get(numberOfCellsPassed + 1).changeState(true);
				path.get(numberOfCellsPassed).changeState(false);
			}
	
		
			if(currentPosition.y + pixelsPassed >= endPoint.y)
			{
				int extraPixels = currentPosition.y + pixelsPassed - endPoint.y;
				nextCell();
				returnPoint = calculate(extraPixels); 

			}else returnPoint = new Point(currentPosition.x, currentPosition.y + pixelsPassed);
	
		}
	
		return returnPoint;
	}
	
	

	private void nextCell() {
		startPoint = endPoint;
		currentPosition = endPoint;
		numberOfCellsPassed++;
		if(numberOfCellsPassed + 1 < path.size())
		{
			if(path.get(numberOfCellsPassed).containsBullet)
				maximumHitPoints = maximumHitPoints - 1;
			if(maximumHitPoints == 0){
				state = State.DEAD;
				setChanged();
				notifyObservers();
			}
			endPoint = createPoint(path.get(numberOfCellsPassed + 1));


		}
	}
	

	
	

	
}
