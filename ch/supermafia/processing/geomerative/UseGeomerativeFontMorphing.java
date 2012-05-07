
package ch.supermafia.processing.geomerative;

import ch.supermafia.framework3D.mathematics.MathUtilities;
import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class UseGeomerativeFontMorphing extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600);
		RG.init(this);
		RG.setPolygonizer(RG.UNIFORMLENGTH);
		RG.setPolygonizerLength(1);
		shapeFont1 = RG.getText("Camille", "Georgia.ttf", 72, CENTER);
		shapeFont2 = RG.getText("Camille", "FreeSans.ttf", 72, CENTER);
		rgPointsF1 = shapeFont1.getPoints();
		rgPointsF2 = shapeFont2.getPoints();
		mu = 0.0f;
		morphedShape = new RShape();

		}
	
	public void draw()
		{
		background(255);
		strokeWeight(5.0f);
		mu = map(mouseX, 0, width, 0, 1);
		translate(width / 2, height / 2);

		drawFontMorphing(rgPointsF1, rgPointsF2);
		}
	
	private void drawFontMorphing(RPoint[] rgPointsF1, RPoint[] rgPointsF2)
		{
		beginShape(POINTS);
		for(int i = 0; i < rgPointsF1.length; i++)
			{
			int u = (int)MathUtilities.map(i, 0, rgPointsF1.length, 0, rgPointsF2.length);
			RPoint rg1 = rgPointsF1[u];
			RPoint rg2 = rgPointsF2[u];
			float x = MathUtilities.cosineInterpolate(rg1.x, rg2.x, mu);
			float y = MathUtilities.cosineInterpolate(rg1.y, rg2.y, mu);
			morphedShape.addMoveTo(new RPoint(x, y));
			vertex(x, y);
			}
		endShape();
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'p':
				RG.saveShape("morph.svg", morphedShape);//FIXME not showing anything in the svg
				break;
			default:
				break;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private RShape shapeFont1;
	private RShape shapeFont2;
	private RPoint[] rgPointsF1;
	private RPoint[] rgPointsF2;
	private RShape morphedShape;
	private float mu;
	}
