
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.UI.QuadGroup;
import ch.supermafia.framework3D.UI.widgets.model.Quad;
import ch.supermafia.framework3D.UI.widgets.view.QuadViewProcessing;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class UITest extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		quadGroup = new QuadGroup();
		
		gfx = new ProcessingGfx(this);
		strokeWeight(2.0f);
		Quad q = new Quad();
		q.setTopLeft(new Vec3D(width / 4, height / 4));
		q.setBottomLeft(new Vec3D(width / 4, height - height / 4));
		q.setBottomRight(new Vec3D(width - width / 4, height - height / 4));
		q.setTopRight(new Vec3D(width - width / 4, height / 4));
		q.setSelectable(true);
		QuadViewProcessing qView = new QuadViewProcessing(gfx, q);
		quadGroup.add(qView);
		}
	
	public void draw()
		{
		background(255);
		quadGroup.draw();
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 's':
				quadGroup.select();
				break;
			case 't':
				quadGroup.deSelect();
			default:
				break;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private QuadGroup quadGroup;
	private ProcessingGfx gfx;
	}
