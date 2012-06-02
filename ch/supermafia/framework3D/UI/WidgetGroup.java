
package ch.supermafia.framework3D.UI;

import java.util.ArrayList;
import java.util.List;

import ch.supermafia.framework3D.UI.model.Widget;
import ch.supermafia.framework3D.UI.view.WidgetView_I;

public class WidgetGroup
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public WidgetGroup()
		{
		widgets = new ArrayList<WidgetView_I>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void add(WidgetView_I widget)
		{
		widgets.add(widget);
		}
	
	public void remove(Widget widget)
		{
		widgets.remove(widget);
		}
	
	public void draw()
		{		
		for(WidgetView_I widgetView:widgets)
			{
			widgetView.draw();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	protected List<WidgetView_I> widgets;
	
	}
