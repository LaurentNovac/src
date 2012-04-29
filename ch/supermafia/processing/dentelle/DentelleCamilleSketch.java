
package ch.supermafia.processing.dentelle;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class DentelleCamilleSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		stroke(255);
		size(1000, 1000);
		step = 6;
		step2 = 2;
		smooth();
		noLoop();
		}
	
	public void draw()
		{
		background(0);
		translate(width / 2, height / 2);
		for(int i = 0; i < n; i++)
			{
			pushMatrix();
			translate(0, height / step2);
			drawMotif();
			popMatrix();
			rotate(angle);
			}
		}
	
	//============================================//
	/**
	 Le pattern correspond au dessin de base qui est rŽpŽtŽ
	 */
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawPattern()
		{
		p1x = -width / step;
		p1y = height / step2;
		p2x = width / step;
		p2y = height / step2;
		line(0, 0, p1x, p1y);
		line(0, 0, p2x, p2y);
		line(p1x, p1y, p2x, p2y);
		}
	
	//============================================//
	
	private void drawMotif()
		{
		for(int i = 0; i < n; i++)
			{
			drawPattern();
			rotate(angle);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int p1x, p1y, p2x, p2y;
	private int step;
	private int step2;
	private float angle = PI / 16;
	private float n = 2 * PI / angle;
	}
