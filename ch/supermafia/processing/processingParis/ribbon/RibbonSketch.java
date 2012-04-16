
package ch.supermafia.processing.processingParis.ribbon;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class RibbonSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, P3D);
		smooth();
		reinit();
		}
	
	public void draw()
		{
		background(20);
		translate(width / 2, height / 2,0);
		drawRibbon();
		}
	
	public void reinit()
		{
		ribn = (int)random(5, 20);
		ribbons = new Ribbon[ribn];
		for(int i = 0; i < ribn; i++)
			{
			ribbons[i] = new Ribbon(this);
			}
		}
	
	public void mousePressed()
		{
		reinit();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawRibbon()
		{
		noFill();
		
		for(int i = 0; i < ribbons.length; i++)
			{
			if (!ribbons[i].isDead())
				{
				ribbons[i].update();
				}
			ribbons[i].draw();
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Ribbon[] ribbons;
	private int ribn;
	}
