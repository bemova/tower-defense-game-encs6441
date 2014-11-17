package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;

import java.util.Map.Entry;

import core.applicationservice.locationservices.PositionService;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.aliens.crittertype.IntelligentCritter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.waves.Position;

public class ShootingService {
	private static final Log4jLogger logger = new Log4jLogger();
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
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range)){
					double dis = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
						if ( dis < nearestDistance) {
							((TowerFeatureDecorator)tower).nearestDistance = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
							((TowerFeatureDecorator)tower).setTarget(entry.getKey());
							((TowerFeatureDecorator)tower).getTarget();
							nearestDistance = positionService.getDistance(tower.getTowerPosition(), entry.getValue());
						}
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return ((TowerFeatureDecorator)tower).getTarget();
	}
	public Critter weakestCritter(Tower tower){
		Critter weakest = null;
		Position weakestPosition =null;
		int lowestLife = Integer.MAX_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range)){
					if(weakest != null){
						if (((Critter)entry.getKey()).getLife() < lowestLife) {
							lowestLife= ((Critter)entry.getKey()).getLife();
							 weakest = entry.getKey();
							 weakestPosition = entry.getValue();
						}
					}else{
						 weakest = entry.getKey();
						 weakestPosition = entry.getValue();
						 lowestLife= ((Critter)entry.getKey()).getLife();
					}
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return weakest;
	}
	public Critter strongestCritter(Tower tower){
		Critter strongest = null;
		Position strongestPosition =null;
		int highestLife = Integer.MIN_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range)){
					if(strongest != null){
						if (((Critter)entry.getKey()).getLife() > highestLife) {
							highestLife= ((Critter)entry.getKey()).getLife();
							strongest = entry.getKey();
							strongestPosition = entry.getValue();
						}
					}else{
						 strongest = entry.getKey();
						 strongestPosition = entry.getValue();
						 highestLife= ((Critter)entry.getKey()).getLife();
					}
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return strongest;
	}
}
