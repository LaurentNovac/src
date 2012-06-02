
package ch.supermafia.framework3D.UI.widgets.view;

import ch.supermafia.framework3D.UI.model.Widget;
import ch.supermafia.framework3D.UI.view.WidgetView_I;
import ch.supermafia.framework3D.UI.widgets.model.Quad;
import ch.supermafia.framework3D.processing.ProcessingGfx;

public class QuadViewProcessing implements WidgetView_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public QuadViewProcessing(ProcessingGfx gfx, Quad quad)
		{
		this.quad = quad;
		this.gfx = gfx;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void draw()
		{
		// TODO use gfx to draw the quad
		System.out.println("Am i selected? :" + quad.isSelected());
		}
	
	@Override
	public void notify(Widget widget)
		{
		this.quad = (Quad)widget;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public Quad getQuad()
		{
		return quad;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private Quad quad;
	
	private ProcessingGfx gfx;
	}
