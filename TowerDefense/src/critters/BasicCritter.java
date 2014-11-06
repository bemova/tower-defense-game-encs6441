package critters;

public class BasicCritter implements  Critter {
	protected 	enum 	CritterState 	{ ISMOVING , ISDEAD, COMPLETEDPATH }
	protected CritterMovingStrategy movement;
	protected CritterState state;
	protected int maximumHitPoints;
	protected int currentHitPoint;
	protected int demageHitPoint;
	public BasicCritter() {
		// TODO Auto-generated constructor stub
	}

}
