
package ch.supermafia.processing.framework3D.use.frameworkDemo;

import java.util.logging.Level;
import java.util.logging.Logger;

import processing.core.PApplet;

public class useSketch3D
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
			PApplet.main(new String[] { "--present", "ch.supermafia.processing.framework3D.use.Sketch3D" });
			}
		catch (Exception e)
			{
			Logger.getLogger("Skecth3D").log(Level.SEVERE, e.getMessage());
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
