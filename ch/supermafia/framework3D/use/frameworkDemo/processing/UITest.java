
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.UI.QuadGroup;
import ch.supermafia.framework3D.UI.widgets.model.Quad;
import ch.supermafia.framework3D.UI.widgets.view.QuadViewProcessing;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class UITest extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600);
		quadGroup = new QuadGroup();
		noLoop();
		}
	
	public void draw()
		{
		
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'a':
				Quad q = new Quad();
				QuadViewProcessing qView = new QuadViewProcessing(q);
				quadGroup.add(qView);
				quadGroup.draw();
				break;
			case 's':
				quadGroup.select();
				quadGroup.draw();
				break;
			case 'd':
				quadGroup.draw();
				break;
			default:
				break;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private QuadGroup quadGroup;
	}
