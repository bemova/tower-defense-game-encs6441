package core.domain.warriors.aliens;

/**
 * <h4>This class is used for decorator design pattern</h4>
 * <b>it is not completed like towers it has to be completed in build 2</b>
 * @author mojtaba
 * @version 0.1
 */
public abstract class CritterFeatureDecorator extends Critter {
	/**
	 * string definition of decorated object that contains the creator and the all features
	 */
	public abstract String getDescription();
	
}
