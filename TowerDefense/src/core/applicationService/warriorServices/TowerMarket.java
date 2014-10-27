package core.applicationService.warriorServices;

import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
/**
 * <b>this class is used for sell functionality</b> 
 * @author team 
 * @version 0.1
 */
public class TowerMarket {
	/**
	 * <b>this method calculate the sell value base on the decorated tower </b>
	 * @param tower
	 * @return tower
	 */
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
