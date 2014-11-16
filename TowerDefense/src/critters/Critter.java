package critters;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import maps.GridCell;

public interface Critter {

	enum 	State 	{ INVISIBLE, MOVING , DEAD, COMPLETEDPATH }
	
	State getState();
	CritterMovingStrategy getMovingBehaviour();
	double getSpeed();
	int getHitPoints();
	double getAppearTime();	
	ArrayList<GridCell> getPath();
	int getNumberOfCellsPassed();
	Point getCurrentPosition();
	Point getStartPoint();
	Point getEnedPoint();
	int getDemageAmount();
	
	
//	void setSpeed(double newspeed);	
	void setAppearTime(long currentInterval);
	void setPath(ArrayList<GridCell> path);
	void setNumberOfCellsPassed(int newPassedCells);
	void setcurrentPosition(Point newPosition);
	void setStartPoint(Point newValue);
	void setEnedPoint(Point newValue);
	void setMovingStrategy(CritterMovingStrategy newMovingStrategy);
	void setDemage();
	void setState( State newState);
	
	
	void updatePosition(long timeMillisecs, Critter critter);
	void draw(Graphics g);
	void notifyregisteredObservers();

}

