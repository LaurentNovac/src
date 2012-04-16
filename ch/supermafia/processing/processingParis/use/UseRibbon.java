package ch.supermafia.processing.processingParis.use;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;


public class UseRibbon
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public static void main(String[] args)
		{
		main();
		}
	
	public static void main()
		{
		    try
				{
				PApplet.main(new String[] { "--present", "ch.supermafia.processing.processingParis.ribbon.RibbonSketch" });
				}
			catch (Exception e)
				{
				Logger.getLogger("dentelle").log(Level.SEVERE,e.getMessage());
				}	
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	}
