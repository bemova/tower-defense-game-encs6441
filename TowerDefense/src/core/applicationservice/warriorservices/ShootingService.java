package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;

import java.util.Map;
import java.util.Map.Entry;

import core.applicationservice.locationservices.PositionService;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.crittertype.IntelligentCritter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.waves.Position;

public class ShootingService {
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * Calculate nearest critter to specific tower
	 * @param tower
	 * @return nearestCritter
	 */
	public Critter nearestCritter(Tower tower){
		double nearestDistance = Double.MAX_VALUE;
		Critter critter = null;
		if(((TowerFeatureDecorator)tower).getTarget() !=null){
			Critter target = ((TowerFeatureDecorator)tower).getTarget();
			Position targetPosition = target.getPath()[target.getCurrentPosition()];
			nearestDistance = ((TowerFeatureDecorator)tower).nearestDistance;
		}else{
			//			Critter target = null;
			//			target.setCurrentPosition(0);
			//			((TowerFeatureDecorator)tower).setTarget(target);
			//			Position targetPosition = new Position(0, 0);
		}
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Position> map =  (((TowerFeatureDecorator)tower).getCrittersLocation());
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			Critter c = null;
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range)){
					double dis = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
					//nearestDistance = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
					if ( dis <= nearestDistance) {
						((TowerFeatureDecorator)tower).nearestDistance = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
						((TowerFeatureDecorator)tower).setTarget(entry.getKey());
						((TowerFeatureDecorator)tower).getTarget();
						nearestDistance = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
						map.remove(entry);
						((TowerFeatureDecorator)tower).setCrittersLocation(map);
					}
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return ((TowerFeatureDecorator)tower).getTarget();
	}
	/**
	 * Calculate weakest Critter based on critter life
	 * @param tower
	 * @return weakestCritter
	 */
	public Critter weakestCritter(Tower tower){
		Critter weakest = null;
		Position weakestPosition =null;
		int lowestLife = Integer.MAX_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Position> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getLife() < lowestLife){
					lowestLife= ((Critter)entry.getKey()).getLife();
					weakest = entry.getKey();
					weakestPosition = entry.getValue();
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return weakest;
	}
	/**
	 * Calculate strongest Critter Critter based on critter life
	 * @param tower
	 * @return strongestCritter
	 */
	public Critter strongestCritter(Tower tower){
		Critter strongest = null;
		Position strongestPosition =null;
		int highestLife = Integer.MIN_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Position> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getLife() > highestLife){
					highestLife= ((Critter)entry.getKey()).getLife();
					strongest = entry.getKey();
					strongestPosition = entry.getValue();
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return strongest;
	}
	/**
	 * Calculate nearest critter to exit gate
	 * @param tower
	 * @param path
	 * @return nearToEndCritter
	 */
	public Critter nearToEndCritter(Tower tower,Position[] path){
		Critter nearToEnd = null;
		Position nearToEndPosition =null;
		int nearestDistance = path.length - 1;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Position> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && ((path.length -1) - entry.getKey().getCurrentPosition())<= nearestDistance){
					nearestDistance= (path.length -1) - entry.getKey().getCurrentPosition();
					nearToEnd = entry.getKey();
					nearToEndPosition = entry.getValue();
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return nearToEnd;
	}
	/**
	 * Calculate nearest critter to entrance gate
	 * @param tower
	 * @param path
	 * @return nearToStartCritter
	 */
	public Critter nearToStartCritter(Tower tower,Position[] path){
		Critter nearToStart = null;
		Position nearToStartPosition =null;
		int nearestDistance = path.length - 1;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Position> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getCurrentPosition() <= nearestDistance){
					nearestDistance= (path.length -1) - entry.getKey().getCurrentPosition();
					nearToStart = entry.getKey();
					nearToStartPosition = entry.getValue();
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return nearToStart;
	}
}
