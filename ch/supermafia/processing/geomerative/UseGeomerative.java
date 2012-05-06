
package ch.supermafia.processing.geomerative;

import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class UseGeomerative extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		strokeWeight(3);
		RG.init(this);
		grp = RG.getText("Hello world!", "Georgia.ttf", 72, CENTER);
		}
	
	public void draw()
		{
		background(255);
		translate(width / 2, height / 2);
		//grp.draw();
		RG.setPolygonizer(RG.UNIFORMLENGTH);

		RG.setPolygonizerLength(map(mouseY, 0, height, 3, 100));
		RPoint[] rgPoints = grp.getPoints();
		beginShape(POINTS);
		for(RPoint rPoint:rgPoints)
			{
			vertex(rPoint.x,rPoint.y);
			}
		endShape();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private RShape grp;
	}
