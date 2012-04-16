
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;

public interface FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public Vec3D f(float x, float y);
	
	public float getUmin();
	
	public float getUMax();
	
	public float getVmin();
	
	public float getVMax();
	}