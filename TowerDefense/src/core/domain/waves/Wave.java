package core.domain.waves;

import java.util.ArrayList;
import java.util.List;

import core.domain.warriors.aliens.Critter;
/**
 * <p>this class was implemented to keep all information about wave of
 *  critters, so we decided to keep only list of critters that are in 
 *  the wave and the position of the wave's head to inform all tower 
 *  the position of the wave by observer design pattern</p> 
 *  <b>this part it will be implemented for the build 2</b>
 *  
 *  @author Team5
 *  @version 0.1
 */
public class Wave {
	public List<Critter> aliens;
	public Position headPosition;
	
	/**
	 * Return first critter position in a wave
	 * 
	 * @return headPosition
	 */
	public Position getHeadPosition() {
		return headPosition;
	}

	/**
	 * Set first critter position in a wave
	 * 
	 * @param headPosition set position of the wave. 
	 */
	public void setHeadPosition(Position headPosition) {
		this.headPosition = headPosition;
	}
	
	/**
	 * create alien as an list of critters
	 */
	public Wave() {
		aliens = new ArrayList<>();
	}

}
