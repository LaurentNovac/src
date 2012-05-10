
package ch.supermafia.processing.mapTweak;

import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.mathematics.MathUtilities;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class MapTweak extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		stroke(0);
		v1 = new Vec3D(0, 0, 0);
		v2 = new Vec3D(width, height, 0);
		}
	
	public void draw()
		{
		background(255);
		Vec3D vInt = MathUtilities.linearInterpolate(v1, v2, map(mouseX, 0, width, 0, 1));
		line(0, 0, 0, vInt.x(), vInt.y(), 0);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Vec3D v1;
	private Vec3D v2;
	
	}
