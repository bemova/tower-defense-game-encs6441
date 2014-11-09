package critters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import core.domain.maps.GridCell;

public class BasicCritter implements Critter {
	
	protected CritterMovingStrategy movement;
	protected Critter.State state = Critter.State.INVISIBLE;
	protected int maximumHitPoints;
	protected int currentHitPoint;
	protected int demageHitPoint;
	protected double speed; // number of pixels per 1 millisecond
	protected long appearTimeout;
	
	ArrayList<GridCell> path;
	public BasicCritter() {
		speed = 15.0 / 1000; // 15 pixel per 1000 milliseconds and 15 / 1000 per on millisecond
		maximumHitPoints = 10;
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
	
	Point currentPosition = null;
	Point startPoint = null;
	Point endPoint = null;
	public int numberOfCellsPassed = 0;
	
	@Override
	public void updatePosition(long timeMillisecs) {
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
		
		int pixelsPassed = (int)(speed * timeMillisecs);
		
		currentPosition = calculate(pixelsPassed);
		
		if(currentPosition == null)
		{
			System.out.println("COMPLETED!!");
			state = Critter.State.COMPLETEDPATH;
		}
		else
			System.out.println("{" + currentPosition.x+", " + currentPosition.y + "}");
	}
	
	private Point calculate(int pixelsPassed) {
		System.out.println("calculate");
		if(startPoint.x > endPoint.x)
		{
			System.out.println("startPoint.x > endPoint.x " + pixelsPassed );
			if(currentPosition.x - pixelsPassed <= endPoint.x)
			{
				nextCell();
				return calculate(endPoint.x - (currentPosition.x - pixelsPassed));
			}
			
			return new Point(currentPosition.x - pixelsPassed, currentPosition.y);
		}
		else if(startPoint.x < endPoint.x)
		{
			System.out.println("startPoint.x < endPoint.x " + pixelsPassed );
			if(currentPosition.x + pixelsPassed >= endPoint.x)
			{
				nextCell();
				return calculate(currentPosition.x + pixelsPassed - endPoint.x);
			}
			
			return new Point(currentPosition.x + pixelsPassed, currentPosition.y);
		}
		else if(startPoint.y > endPoint.y)
		{
			System.out.println("startPoint.y > endPoint.y " + pixelsPassed );
			if(currentPosition.y - pixelsPassed <= endPoint.y)
			{
				nextCell();
				return calculate(endPoint.y - (currentPosition.y - pixelsPassed));
			}
			
			return new Point(currentPosition.x, currentPosition.y - pixelsPassed);
		}
		else if(startPoint.y < endPoint.y)
		{
			System.out.println("startPoint.y < endPoint.y " + pixelsPassed );
			if(currentPosition.y + pixelsPassed >= endPoint.y)
			{
				nextCell();
				return calculate(currentPosition.y + pixelsPassed - endPoint.y);
			}
			
			return new Point(currentPosition.x, currentPosition.y + pixelsPassed);
		}
	
		return null;
	}
	private void nextCell() {
		startPoint = endPoint;
		currentPosition = endPoint;
		numberOfCellsPassed++;
		if(numberOfCellsPassed + 1 < path.size())
		{
			endPoint = createPoint(path.get(numberOfCellsPassed + 1));
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
			g.fillOval(currentPosition.x, currentPosition.y, 10, 10);
			g.setColor(c);
		}		
	}	
}