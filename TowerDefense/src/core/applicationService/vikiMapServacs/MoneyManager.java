package core.applicationService.vikiMapServacs;

public class MoneyManager {
	private double money;
	
	public MoneyManager() {
		money = 200;
		
	}
	
	public double get(){
		return money;
		
	}
	
	public double update(String buysell, double amount ){
		double returnVAlue = money;
		if(buysell.equals("buy")) returnVAlue = returnVAlue - amount;
		else returnVAlue = returnVAlue + amount;
		if(returnVAlue >= 0) money = returnVAlue;
		
		return  returnVAlue;
	}
	
	

}
