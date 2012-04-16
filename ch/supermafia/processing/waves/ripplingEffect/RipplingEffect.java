
package ch.supermafia.processing.waves.ripplingEffect;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class RipplingEffect extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
			size(400,400);
			img = createImage(width, height, RGB);
			frameRate(200);
		}
	
	public void draw()
		{
		background(0);
		Logger.getLogger("Frame Rate").log(Level.INFO,String.valueOf(frameRate));
		double t=millis()/10;
		for(int x=0;x<width;x++)
			{
			for(int y = 0; y < height; y++)
				{
				int c=(int)color_ripling((double)x, (double)y, t);
				stroke(c);
				img.set(x,y,color(c,c,c));
				}
			}
		image(img,0,0);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/**
	 * <pre>
	 * compute the pixel color of a squared 
	 * image using the rippling effect
	 * </pre>
	 * 
	 * @param x abscissa pixel coordinate
	 * @param y	ordinate pixel coordinate
	 * @param t time
	 * @return grayscale color [0,255]
	 */
	private double color_ripling(double x, double y, double t)
		{
		double dim=width;
		double f_x=x-(dim/2.0);
		double f_y=y-(dim/2.0);
		double d=Math.sqrt(f_x*f_x+f_y*f_y);
		
		double d1=Math.cos((d/10.0)-(t/7.0));
		double d2=(d/10.0)+1.0;
		return 128.0+127.0*(d1/d2);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static PImage img;
	}
