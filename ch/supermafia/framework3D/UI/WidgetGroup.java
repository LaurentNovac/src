
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
		widgets = new ArrayList<Widget>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void add(Widget widget)
		{
		widgets.add(widget);
		}
	
	public void remove(Widget widget)
		{
		widgets.remove(widget);
		}
	
	public void draw()
		{
		System.out.println("....");
		for(Widget widget:widgets)
			{
			for(WidgetView_I widgetView:widget)
				{
				widgetView.draw();
				}
			}
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
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	protected List<Widget> widgets;
	
	}
