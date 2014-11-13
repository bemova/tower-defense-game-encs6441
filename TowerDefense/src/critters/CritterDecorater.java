package critters;

import java.util.Observable;

public abstract class CritterDecorater extends Observable implements  Critter {
protected final Critter criterObject;
	

public CritterDecorater(Critter newcriterObject) {
	criterObject = newcriterObject;
	}

}
