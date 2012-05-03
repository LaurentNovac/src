
package ch.supermafia.framework3D.mathematics.Function;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class SinDistSquared implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		return new Vec3D(x, y, (float)Math.sin(Math.sqrt(x * x + y * y)));
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return -10;
		}
	
	@Override
	public float getUMax()
		{
		return 10;
		}
	
	@Override
	public float getVmin()
		{
		return -10;
		}
	
	@Override
	public float getVMax()
		{
		return 10;
		}

	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	}
