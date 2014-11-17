package MapServacs;

import java.util.Observable;
import java.util.Observer;

import UI.IUserInterface;
import UI.UserInterface;
import critters.Critter;

public class MoneyManager implements Observer {
	private double money;
	IUserInterface Uinterface;

	public MoneyManager(IUserInterface uinterface) {
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

		Critter critter = (Critter)arg0;
		if (critter.getState() == Critter.State.DEAD || critter.getState() == Critter.State.COMPLETEDPATH) {
			int value = critter.getHitPoints() - critter.getDemageAmount();
			if (value < 0)
				money = money + critter.getHitPoints();
			else
				money = money - critter.getHitPoints();

			Uinterface.moneyView().setText("" + money);
		}

	}

}
