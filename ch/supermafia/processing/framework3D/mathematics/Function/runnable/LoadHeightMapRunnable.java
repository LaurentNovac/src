
package ch.supermafia.processing.framework3D.mathematics.Function.runnable;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class LoadHeightMapRunnable implements Runnable
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public LoadHeightMapRunnable(int tid, int nbThread, float minHeight, float maxHeight, BufferedImage image, float[] hMap)
		{
		this.tid = tid;
		this.nbThread = nbThread;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.image = image;
		this.hMap = hMap;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void run()
		{
		image.getSubimage(0, 0, 10, 10);
		while(tid < image.getWidth())
			{
			for(int y = 0; y < image.getHeight(); y++)
				{
				Color col = new Color(image.getRGB(tid, y));
				hMap[index(tid, y)] = minHeight + ((col.getRed() + col.getBlue() + col.getGreen()) / 765f) * (maxHeight - minHeight);
				}
			tid += nbThread;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private int index(int x, int y)
		{
		return y * image.getWidth() + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	int tid;
	int nbThread;
	float minHeight;
	float maxHeight;
	private BufferedImage image;
	private float[] hMap;
	}
