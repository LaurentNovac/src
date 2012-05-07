
package ch.supermafia.framework3D.population.model;

import java.util.ArrayList;
import java.util.Iterator;

import ch.supermafia.framework3D.population.observer.Observable_I;
import ch.supermafia.framework3D.population.observer.Observer_I;


public class Population implements Observable_I ,Iterable<Individuals>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Population()
		{
		listViews = new ArrayList<Observer_I>();
		listIndividuals = new ArrayList<Individuals>();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public ArrayList<Individuals> getListIndividuals()
		{
		return this.listIndividuals;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void registerObserver(Observer_I observer)
		{
		listViews.add(observer);
		}

	@Override
	public void notifyObservers(Population population)
		{
		for(Observer_I observer:listViews)
			{
			observer.notify(population);
			}
		}

	@Override
	public void unregisterObserver(Observer_I observer)
		{
		listViews.remove(observer);
		}

	public void addIndividuals(Individuals individuals)
		{
		listIndividuals.add(individuals);
		notifyObservers(this);
		}

	public void removeIndividuals(Individuals individuals)
		{
		listIndividuals.remove(individuals);
		notifyObservers(this);
		}

	@Override
	public Iterator<Individuals> iterator()
		{
		return listIndividuals.iterator();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//tools
	private ArrayList<Observer_I> listViews;
	//output
	private ArrayList<Individuals> listIndividuals;

	}
