
package ch.supermafia.framework3D.population.observer;

import ch.supermafia.framework3D.population.model.Population;


public interface Observable_I
	{

	public void registerObserver(Observer_I observer);

	public void notifyObservers(Population population);

	public void unregisterObserver(Observer_I observer);

	}
