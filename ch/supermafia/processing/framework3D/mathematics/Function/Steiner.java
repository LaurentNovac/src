
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;

public class Steiner implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float x_ = (float)(Math.cos(y) * Math.cos(y) * Math.sin(2 * x) / 2);
		float y_ = (float)(Math.sin(x) * Math.sin(2 * y) / 2);
		float z_ = (float)(Math.cos(x) * Math.sin(2 * y) / 2);
		return new Vec3D(x_, y_, z_);
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return 0;
		}
	
	@Override
	public float getUMax()
		{
		return (float)Math.PI;
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return (float)Math.PI;
		}
	
	}
