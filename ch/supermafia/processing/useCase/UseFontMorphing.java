package ch.supermafia.processing.useCase;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;


public class UseFontMorphing
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
			PApplet.main(new String[] { "--present", "ch.supermafia.processing.geomerative.UseGeomerativeFontMorphing" });
			}
		catch (Exception e)
			{
			Logger.getLogger("Font morphing").log(Level.SEVERE,e.getMessage());
			}	
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
