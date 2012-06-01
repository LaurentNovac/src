
package ch.supermafia.framework3D.UI.view;

import ch.supermafia.framework3D.UI.model.Widget;

public interface WidgetView_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void draw();
	
	public void notify(Widget widget);
	
	}
