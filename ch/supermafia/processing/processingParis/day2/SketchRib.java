
package ch.supermafia.processing.processingParis.day2;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class SketchRib extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600, P3D);
		smooth();	
		n = (int)random(5, 20);
		part = new Particle[n];
		for(int i = 0; i < n; i++)
			{
			part[i] = new Particle(this);
			}
		frameRate(5);
		}
	
	public void draw()
		{
		background(0);
		noFill();
		translate(width / 2, height / 2, 0);
		for(int i = 0; i < n; i++)
			{
			part[i].draw();
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Particle[] part;
	private int n;
	}
