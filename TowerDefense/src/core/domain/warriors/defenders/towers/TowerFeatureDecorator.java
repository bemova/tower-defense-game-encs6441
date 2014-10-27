package core.domain.warriors.defenders.towers;

/**
 * </b>This class is used for decorator design pattern for towers</b>
 * 
 * @author mojtaba
 * @version 0.1
 */
public abstract class TowerFeatureDecorator extends Tower {
	/**
	 * <b>it is a signature of getting description of the tower that is means it can
	 *  return tower type and the all its features as a String</b> 
	 */
	public abstract String getDescription();
	
}
