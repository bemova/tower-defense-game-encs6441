package critters;

import java.awt.Point;

public interface CritterMovingStrategy {
	
	Point executeMovement(long timeMillisecs, Critter critter); 


}
