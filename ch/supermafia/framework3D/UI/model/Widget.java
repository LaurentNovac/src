
package ch.supermafia.framework3D.UI.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.supermafia.framework3D.UI.view.WidgetView_I;

public class Widget implements Iterable<WidgetView_I>
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Widget()
		{
		views = new ArrayList<WidgetView_I>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public Iterator<WidgetView_I> iterator()
		{
		return views.iterator();
		}
	
	public void add(WidgetView_I view)
		{
		views.add(view);
		}
	
	public void remove(WidgetView_I view)
		{
		views.remove(view);
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	protected void notifyViews()
		{
		for(WidgetView_I view:views)
			{
			view.notify(this);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private List<WidgetView_I> views;
	
	}
