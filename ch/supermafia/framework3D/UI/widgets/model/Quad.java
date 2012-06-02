
package ch.supermafia.framework3D.UI.widgets.model;

import ch.supermafia.framework3D.UI.model.Widget;

public class Quad extends Widget
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Quad()
		{
		isSelected = false;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setSelected(boolean isSelected)
		{
		this.isSelected = isSelected;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public boolean isSelected()
		{
		return isSelected;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private boolean isSelected;
	
	}
