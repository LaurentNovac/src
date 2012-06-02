
package ch.supermafia.framework3D.UI;

import ch.supermafia.framework3D.UI.view.WidgetView_I;
import ch.supermafia.framework3D.UI.widgets.view.QuadViewProcessing;

public class QuadGroup extends WidgetGroup
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void select()
		{
		for(WidgetView_I widget:widgets)
			{
			QuadViewProcessing q = (QuadViewProcessing)widget;
			q.getQuad().setSelected(true);
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
	
	}
