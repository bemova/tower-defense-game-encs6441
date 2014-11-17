package core.domain.warriors.defenders.towers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import core.applicationservice.informerservices.Observer;
import core.applicationservice.locationservices.PositionService;
import core.applicationservice.warriorservices.ShootingService;
import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
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
	public void alienUpdate(Position alienPosition, Critter critter,String shootingStrategy) {
		crittersLocation.put(critter, alienPosition);
		Map<Critter, Position> map = this.getCrittersLocation();
		Set<Entry<Critter, Position>> it =  map.entrySet();
		ShootingService service =  new ShootingService();
		PositionService positionService = new PositionService();
		TowerFactory factory = new TowerFactory();
		int range = factory.getRange(this);
		if(positionService.isInRange(this.getTowerPosition(), 
				critter.getPath()[critter.getCurrentPosition()], range) && critter.getLife() > 0){
			map.put(critter, alienPosition);
			this.setCrittersLocation(map);
			Critter c = null;
			if(shootingStrategy == null){
				this.setShootingStrategy( DefenderConstants.NearToEnd_Strategy);
				c = service.nearToStartCritter(this, critter.getPath());
			}else{
				switch (shootingStrategy) {
				case DefenderConstants.NearToEnd_Strategy:
					c = service.nearToEndCritter(this, critter.getPath());
					break;

				case DefenderConstants.NearToStart_Strategy:
					c = service.nearToStartCritter(this, critter.getPath());
					break;

				case DefenderConstants.Strangest_Strategy:
					c = service.strongestCritter(this);
					break;

				case DefenderConstants.Weakest_Strategy:
					c = service.weakestCritter(this);
					break;
				}
			}
			if(c !=null){
				setTarget(c);
				setChanged();
				notifyObservers();
			}
		}else{
			for (Entry<Critter, Position> entry : it) {
				if(entry.getKey() == critter){
					map.remove(entry);
					this.setCrittersLocation(map);
					break;
				}
			}
		}




	}

	public void removeDeadCritter(Critter critter) {
		try {
			crittersLocation.remove(critter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * <b>it is a signature of getting description of the tower that is means it
	 * can return tower type and the all its features as a String</b>
	 * 
	 * @return descrition of the tower
	 */
	public abstract String getDescription();

	public void register(Subject defenderInformer) {
		this.subject = defenderInformer;
		this.subject.registerObserver(this);
	}

	public Critter getTarget() {
		return target;
	}

	public TowerFeatureDecorator getDefender() {
		return this;
	}

	public void setTarget(Critter target) {
		this.target = target;
	}

}
