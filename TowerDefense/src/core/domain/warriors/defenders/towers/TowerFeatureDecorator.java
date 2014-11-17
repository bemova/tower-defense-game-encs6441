package core.domain.warriors.defenders.towers;

import java.util.Map;

import core.applicationservice.informerservices.Observer;
import core.applicationservice.warriorservices.ShootingService;
import core.domain.Subject;
import core.domain.warriors.aliens.Critter;
import core.domain.waves.Position;

/**
 * <b>This class is used for decorator design pattern for towers</b>
 * 
 * @author Team5
 * @version 0.1
 */
public abstract class TowerFeatureDecorator extends Tower implements Observer {
	private Critter target;
	public Map<Critter, Position> crittersLocation;
	public double nearestDistance;
	
	public Map<Critter, Position> getCrittersLocation() {
		return crittersLocation;
	}
	public void setCrittersLocation(Map<Critter, Position> crittersLocation) {
		crittersLocation = crittersLocation;
	}
	/**
	 * it is implemented for having observer design pattern
	 */
	public void alienUpdate(Position alienPosition, Critter critter) {
		crittersLocation.put(critter, alienPosition);
		ShootingService service =  new ShootingService();
		Critter c = service.nearestCritter(this);
		if(c !=null){
			//setTarget(c);
			setChanged();
			notifyObservers();
		}
		
	}
	public void removeDeadCritter(Critter critter){
		try {
			crittersLocation.remove(critter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * <b>it is a signature of getting description of the tower that is means it can
	 *  return tower type and the all its features as a String</b> 
	 *  @return descrition of the tower
	 */
	public abstract String getDescription();
	public void register(Subject defenderInformer) {
		this.subject = defenderInformer;
		this.subject.registerObserver(this);
	}
	public Critter getTarget() {
		return target;
	}
	
	public TowerFeatureDecorator getDefender(){
		return this;
	}
	public void setTarget(Critter target) {
		this.target = target;
	}

	
}
