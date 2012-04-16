
package ch.supermafia.processing.fractals;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class MandelBrot extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600);
		colorMode(HSB, 360, 100, 100);
		minRe = -1.3968;
		maxRe = -1.3578;
		minIm = -0.03362;
		maxIm = -0.0013973;
		//	minRe=-2.0;
		//	maxRe=1.0;
		//	minIm=-1.2;
		//	maxIm=1.2;
		reFactor = (maxRe - minRe) / (width - 1);
		imFactor = (maxIm - minIm) / (height - 1);
		N = 30;
		img = createImage(width, height, RGB);
		}
	
	public void draw()
		{
		background(0);
		N++;
		drawMandelBrot();
		image(img, 0, 0);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private boolean isInside(double c_im, double c_re, double Z_re, double Z_im)
		{
		boolean isInside = true;
		for(int i = 0; i < N; i++)
			{
			if (Z_re * Z_re + Z_im * Z_im > 4)
				{
				isInside = false;
				h = i; //h is the first iteration that detect that the element is divergent
				break;
				}
			//(a + bi)^2 = a^2 - b^2 + 2 abi
			double Z_im2 = Z_im * Z_im;
			Z_im = 2 * Z_re * Z_im + c_im;
			Z_re = Z_re * Z_re - Z_im2 + c_re;
			}
		return isInside;
		}
	
	private void drawMandelBrot()
		{
		for(int y = 0; y < height; ++y)
			{
			double c_im = maxIm - y * imFactor;
			for(int x = 0; x < width; ++x)
				{
				double c_re = minRe + x * reFactor;
				
				// Calculate whether c belongs to the Mandelbrot set or
				// not and draw a pixel at coordinates (x,y) accordingly
				double Z_re = c_re, Z_im = c_im; // Set Z = c
				if (!isInside(c_im, c_re, Z_re, Z_im))
					{
					img.set(x, y, color(h, 100, 100));
					}
				else
					{
					img.set(x, y, color(0, 0, 0));
					}
				}
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static double minRe;
	private static double maxRe;
	private static double minIm;
	private static double maxIm;
	private static double reFactor;
	private static double imFactor;
	private static PImage img;
	private static int N;
	private static int h;
	}
