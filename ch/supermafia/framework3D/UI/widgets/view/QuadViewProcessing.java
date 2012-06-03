
package ch.supermafia.framework3D.UI.widgets.view;

import ch.supermafia.framework3D.UI.view.WidgetView_I;
import ch.supermafia.framework3D.UI.widgets.model.Quad;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
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
		checkMouse();
		if (quad.isSelected() == true)
			{
			gfx.strokeColor(255, 0, 0, 255);
			gfx.noFill();
			}
		else
			{
			gfx.strokeColor(0, 0, 255, 255);
			gfx.noFill();
			}
		gfx.quad(quad.getTopLeft(), quad.getBottomLeft(), quad.getBottomRight(), quad.getTopRight());
		gfx.ellipse(quad.getTopLeft(), 20, 20);
		gfx.ellipse(quad.getBottomLeft(), 20, 20);
		gfx.ellipse(quad.getBottomRight(), 20, 20);
		gfx.ellipse(quad.getTopRight(), 20, 20);
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
	private void checkMouse()
		{
		System.out.println("HEllo mouse");
		if (gfx.mousePressed() && quad.isSelected())
			{
			Vec3D[] v = quad.getVertices();
			Vec3D mV = gfx.mousePos();
			for(int i = 0; i < 4; i++)
				{
				if (v[i].distanceTo(mV) <= 10)
					{
					System.out.println("Hello mmm");
					v[i].setX(mV.getX());
					v[i].setY(mV.getY());
					}
				}
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private Quad quad;
	
	private ProcessingGfx gfx;
	}
