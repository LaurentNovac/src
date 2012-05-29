
package ch.supermafia.processing.cellularAutomata;

import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class CASketch2D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 800);
		imageDst = new PImage(width, height);
		imageSrc = new PImage(width, height);
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
			if (thread != null) try
				{
				thread.join();
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			stepRule942();
			image(imageDst, 0, 0);
			}
		
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
				if (i == width / 2 && j == width / 2)
					{
					imageSrc.set(i, j, black);
					imageDst.set(i, j, black);
					}
				else
					{
					imageSrc.set(i, j, white);
					imageDst.set(i, j, white);
					}
				}
			}
		}
	
	private void stepRule1022()
		{
		for(int i = 1; i < width - 1; i++)
			{
			for(int j = 1; j < height - 1; j++)
				{
				if (imageSrc.get(i - 1, j) == black || imageSrc.get(i + 1, j) == black || imageSrc.get(i, j + 1) == black || imageSrc.get(i, j - 1) == black)
					{
					imageDst.set(i, j, black);
					}
				}
			}
		saveStep();
		}
	
	private void stepRule942()
		{
		System.out.println("render");
		for(int i = 1; i < width - 1; i++)
			{
			for(int j = 1; j < height - 1; j++)
				{
				int count = 0;
				if (imageSrc.get(i - 1, j) == black)
					{
					count++;
					}
				if (imageSrc.get(i + 1, j) == black)
					{
					count++;
					}
				if (imageSrc.get(i, j - 1) == black)
					{
					count++;
					}
				if (imageSrc.get(i, j + 1) == black)
					{
					count++;
					}
				if (count == 1)
					{
					imageDst.set(i, j, black);
					}
				else
					{
					imageDst.set(i, j, imageSrc.get(i, j));
					}
				}
			}
		saveStep();
		}
	
	private void saveStep()
		{
		System.out.println("save");
		thread = new Thread(new Runnable()
			{	
				@Override
				public void run()
					{
					for(int i = 0; i < width; i++)
						{
						for(int j = 0; j < height; j++)
							{
							imageSrc.set(i, j, imageDst.get(i, j));
							}
						}
					}
			});
		thread.start();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private PImage imageDst;
	private PImage imageSrc;
	private int black;
	private int white;
	private int step;
	private Thread thread;
	}
