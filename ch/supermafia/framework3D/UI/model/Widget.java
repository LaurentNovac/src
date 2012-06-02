
package ch.supermafia.framework3D.UI.model;

public class Widget
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Widget()
		{
		isSelected = false;
		isSelectable = false;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setSelected(boolean isSelected)
		{
		System.out.println("Hello select");
		if (!isSelectable())
			{
			this.isSelected = false;
			}
		else
			{
			this.isSelected = isSelected;
			}
		}
	
	public void setSelectable(boolean isSelectable)
		{
		this.isSelectable = isSelectable;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public boolean isSelected()
		{
		return isSelected;
		}
	
	public boolean isSelectable()
		{
		return isSelectable;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	protected boolean isSelected;
	protected boolean isSelectable;
	}
