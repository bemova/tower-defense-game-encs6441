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
		
		if(buysell.equals("buy")) money = money - amount;
		else money = money + amount;
		return  money;
	}
	
	

}
