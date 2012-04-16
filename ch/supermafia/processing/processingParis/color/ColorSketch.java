
package ch.supermafia.processing.processingParis.color;

import processing.core.PApplet;
import unlekker.util.UColorTool;

@SuppressWarnings("serial")
public class ColorSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600);
		smooth();
		n = (int)random(50, 100);
		o = new Obj[n];
		for(int i = 0; i < n; i++)
			{
			o[i]=new Obj(this);
			}
		}
	
	public void draw()
		{
		background(0);
		noStroke();
		
		for(int i = 0; i < n; i++)
			{
			o[i].draw();
			}
		}
	
	public void reinit()
		{
		for(int i = 0; i < n; i++)
			{
			o[i].initColors();
			}
		}
	
	public void mousePressed()
		{
		reinit();
		}
	
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int n;
	private Obj[] o;
	private UColorTool colors;

	}
