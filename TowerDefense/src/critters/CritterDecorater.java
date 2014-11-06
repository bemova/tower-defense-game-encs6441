package critters;

public abstract class CritterDecorater implements  Critter {
protected final Critter criterObject;
	

public CritterDecorater(Critter newcriterObject) {
	criterObject = newcriterObject;
	}

}
