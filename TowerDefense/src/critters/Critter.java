package critters;

import java.awt.Graphics;
import java.util.ArrayList;

import core.domain.maps.GridCell;

public interface Critter {

	enum 	State 	{ INVISIBLE, MOVING , DEAD, COMPLETEDPATH }
	
	State getState();
	void setMovingBehaviour(CritterMovingStrategy newBehaviour);
	CritterMovingStrategy getMovingBehaviour();
	void setSpeed(double newspeed);
	double getSpeed();
	void updatePosition(long timeMillisecs);
	void setAppearTime(long currentInterval);
	double getAppearTime();
	void setPath(ArrayList<GridCell> path);
	void draw(Graphics g);
	int getHitPoints();
}

