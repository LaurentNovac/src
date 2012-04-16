package ch.supermafia.processing.sketches;

import processing.core.PApplet;

public class Sketch3 extends PApplet
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	

	public void setup()
		{
		size(640,480);
		stroke(255);
		smooth();
		float step=120;
		float angle=PI/step;
		float cX=width/2;
		float cY=height/2;
		float padding=100;
		float radius=((float)width/2)-padding;
		float secondRadius=0;
		float points=300;
		
		background (0);
		drawPolyStar(angle, cX, cY, radius,secondRadius, points);		
		}
	public void draw()
		{
		
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	

	private void drawPolyStar(float angle, float cX, float cY, float radius,float secondRadius,float points)
		{
		for(int i=0;i<points;i++)
			{
			float actualRadius;
			if(i%2==0&&secondRadius!=0)
				{
				actualRadius=secondRadius;
				}
			else
				{
				actualRadius=radius;
				}
			float px = actualRadius*cos(angle)+cX;
			float py = actualRadius*sin(angle)+cY;
			line(cX,cY,px,py);
			angle+=points;
			}
		}
	 }
