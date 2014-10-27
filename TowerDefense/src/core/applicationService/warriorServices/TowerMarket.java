package core.applicationService.warriorServices;

import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;

public class TowerMarket {
	public <T extends Tower> long sellTower(T tower){
		long amount = 0;
		try {
				amount = (tower.cost() * DefenderConstants.Sell_Percentage)/100;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return amount;
	}
}
