package ch.supermafia.processing.useCase;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;


public class UseNodeLines
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
			PApplet.main(new String[] { "--present", "ch.supermafia.processing.toxiclibs.node.NodeLines" });
			}
		catch (Exception e)
			{
			Logger.getLogger("Node lines").log(Level.SEVERE, e.getMessage());
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
