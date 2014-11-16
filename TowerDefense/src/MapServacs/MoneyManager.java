package MapServacs;

import java.util.Observable;
import java.util.Observer;

import UI.UserInterface;
import critters.Critter;

public class MoneyManager implements Observer {
	private double money;
	UserInterface Uinterface;
	
	public MoneyManager(UserInterface uinterface) {
		money = 200;
		Uinterface = uinterface;
	}

	public double get() {
		return money;

	}

	public double setState(String buysell, double amount) {
		double returnVAlue = money;
		if (buysell.equals("buy"))
			returnVAlue = returnVAlue - amount;
		else
			returnVAlue = returnVAlue + amount;
		if (returnVAlue >= 0)
			money = returnVAlue;

		return returnVAlue;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(((Critter)arg0).getState() == Critter.State.DEAD  || ((Critter)arg0).getState() == Critter.State.COMPLETEDPATH){
		int value = ((Critter)arg0).getHitPoints() - ((Critter)arg0).getDemageAmount();
		if(value > 0) money = money + value;
		else
			money = money - ((Critter)arg0).getHitPoints();
		
		Uinterface.moneyView.setText("" + money);
		}
		
	}

}
