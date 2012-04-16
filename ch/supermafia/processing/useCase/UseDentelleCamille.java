package ch.supermafia.processing.useCase;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;


public class UseDentelleCamille
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
				PApplet.main(new String[] { "--present", "ch.supermafia.processing.dentelle.DentelleCamille" });
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
