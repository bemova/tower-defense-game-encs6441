package core.domain;

import core.applicationservice.informerServices.Observer;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();

}
