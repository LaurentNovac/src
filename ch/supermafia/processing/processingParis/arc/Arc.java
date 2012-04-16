
package ch.supermafia.processing.processingParis.arc;

import processing.core.PApplet;
import processing.core.PConstants;

public class Arc
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Arc(Arcs01Ex parent)
		{
		this.parent = parent;
		a = parent.random(PConstants.TWO_PI);
		b = a + PApplet.radians(parent.random(parent.getMinDeg(), parent.getMinDeg() + parent.getDegDelta()));
		r1 = parent.random(parent.getMinRad(), parent.getMinRad() + parent.getRadDelta()) * parent.width;
		r2 = parent.random(0.25f, 0.5f) * (parent.getMinRad() + parent.getMinRad() + parent.getRadDelta()) * parent.width * parent.getRadMode();
		steps = parent.getStepsMode() * (int)(PApplet.degrees(b - a) / 5);//steps is an integer but it is stored as a float for convenience
		stepsDeg = (b - a) / steps;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void draw()
		{
		float x, y;
		for(float i = 0; i < steps; i++)
			{
			x = PApplet.cos(a + i * stepsDeg);
			y = PApplet.sin(a + i * stepsDeg);
			parent.line(x * r1, y * r1, x * r2, y * r2);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private float a;//start angle
	private float b;//end angle
	private float r1;//inner radius
	private float r2;//outer radius
	private float steps;//number of lines drawn 
	private float stepsDeg;
	private float stepsMode;
	private Arcs01Ex parent;
	
	}
