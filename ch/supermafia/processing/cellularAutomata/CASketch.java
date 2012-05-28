
package ch.supermafia.processing.cellularAutomata;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class CASketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 800);
		imageP = new PImage(width, height);
		black = color(0, 0, 0);
		white = color(255, 255, 255);
		step = 0;
		initialState();
		}
	
	public void draw()
		{
		if (step < height)
			{
			step++;
			stepRule90();
			image(imageP, 0, 0);
			}
		else
			{
			step = 0;
			initialState();
			}
		
		}
	
	public void mousePressed()
		{
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void initialState()
		{
		for(int i = 0; i < width; i++)
			{
			for(int j = 0; j < height; j++)
				{
				imageP.set(i, j, white);
				}
			}
		for(int i = 0; i < width; i++)
			{
			imageP.set(width / 2, step, black);
			}
		}
	
	private void stepRule90()
		{
		for(int i = 1; i < width - 1; i++)
			{
			if ((imageP.get(i - 1, step - 1) == black) ^ (imageP.get(i + 1, step - 1) == black))
				{
				imageP.set(i, step, black);
				}
			}
		}
	
	private void stepRule30()
		{
		for(int i = 1; i < width - 1; i++)
			{
			if ((imageP.get(i + 1, step - 1) == white) && (imageP.get(i, step - 1) == white))
				{
				imageP.set(i, step, imageP.get(i - 1, step - 1));
				}
			else
				{
				if (imageP.get(i - 1, step - 1) == black)
					{
					imageP.set(i, step, white);
					}
				else
					{
					imageP.set(i, step, black);
					}
				}
			}
		}
	
	private void stepRule110()
		{
		for(int i = 1; i < width - 1; i++)
			{
			if ((imageP.get(i - 1, step - 1) == black) && (imageP.get(i + 1, step - 1) == black) && (imageP.get(i, step - 1)) == black)
				{
				imageP.set(i, step, white);
				}
			if ((imageP.get(i - 1, step - 1) == black) && (imageP.get(i, step - 1)) == black && (imageP.get(i + 1, step - 1) == white))
				{
				imageP.set(i, step, black);
				}
			if ((imageP.get(i - 1, step - 1) == black) && (imageP.get(i, step - 1)) == white && (imageP.get(i + 1, step - 1) == black))
				{
				imageP.set(i, step, black);
				}
			if ((imageP.get(i - 1, step - 1) == black) && (imageP.get(i, step - 1)) == white && (imageP.get(i + 1, step - 1) == white))
				{
				imageP.set(i, step, white);
				}
			if ((imageP.get(i - 1, step - 1) == white) && (imageP.get(i, step - 1)) == black && (imageP.get(i + 1, step - 1) == black))
				{
				imageP.set(i, step, black);
				}
			if ((imageP.get(i - 1, step - 1) == white) && (imageP.get(i, step - 1)) == black && (imageP.get(i + 1, step - 1) == white))
				{
				imageP.set(i, step, black);
				}
			if ((imageP.get(i - 1, step - 1) == white) && (imageP.get(i, step - 1)) == white && (imageP.get(i + 1, step - 1) == black))
				{
				imageP.set(i, step, black);
				}
			if ((imageP.get(i - 1, step - 1) == white) && (imageP.get(i, step - 1)) == white && (imageP.get(i + 1, step - 1) == white))
				{
				imageP.set(i, step, white);
				}
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private PImage imageP;
	private int black;
	private int white;
	private int step;
	}
