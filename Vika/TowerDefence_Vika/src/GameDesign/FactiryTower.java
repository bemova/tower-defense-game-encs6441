package GameDesign;

public abstract class FactiryTower {
	
	Tower creatTower(String tyepeOfTower, String levelOfTower){
		Tower obj = null;
		if( tyepeOfTower.equals("freezing"))
			obj = new FreezingTower();
		else
			if( tyepeOfTower.equals("firing"))
					obj = new FiringTower();
					
		return new Tower();
	};
	

}
