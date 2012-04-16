
package ch.supermafia.processing;

import processing.core.PApplet;

public class Tibi extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600, P3D);
		background(0);
		smooth();
		rad=width/4;
		nbPoint=400;
		stroke(255);
		strokeWeight(3);
		noFill();
		}
	
	public void draw()
		{
		background(0);
		translate(width / 2, height / 2);
		circle();
		}
	
	public void mousePressed()
		{
		nbPoint++;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void circle()
		{
		
		float deltaAngle=(float)(360.0f/nbPoint);
		for(float i = 0; i < 360; i+=deltaAngle)
			{
			float x=rad*cos(i);
			float y=rad*sin(i);
			point(x,y);
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private float rad;
	private float nbPoint;
	}
