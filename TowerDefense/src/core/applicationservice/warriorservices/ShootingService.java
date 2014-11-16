package core.applicationservice.warriorservices;

import infrastructure.loggin.Log4jLogger;

import java.util.Map.Entry;

import core.applicationservice.locationservices.PositionService;
import core.domain.warriors.aliens.Critter;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.TowerFeatureDecorator;
import core.domain.waves.Position;

public class ShootingService {
	private static final Log4jLogger logger = new Log4jLogger();
	public Critter nearestCritter(Tower tower){
		Critter nearest = null;
		Position nearestPosition =null;
		double nearestDistance = Double.MAX_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Iterable<Entry<Critter, Position>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Position> entry : it) {
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range)){
					if(nearest != null){
						if (positionService.getDistance(nearestPosition, tower.getTowerPosition()) < nearestDistance) {
							nearestDistance= positionService.getDistance(nearestPosition, tower.getTowerPosition());
							 nearest = entry.getKey();
							 nearestPosition = entry.getValue();
						}
					}else{
						 nearest = entry.getKey();
						 nearestPosition = entry.getValue();
						 nearestDistance = positionService.getDistance(nearestPosition, tower.getTowerPosition());
					}
				}
			}
		} catch (Exception e) {
			logger.writer(this.getClass().getName(), e);
		}
		return nearest;
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
