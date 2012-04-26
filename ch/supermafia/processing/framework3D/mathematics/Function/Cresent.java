
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class Cresent implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float x_ = (float)((2 + Math.sin(2 * Math.PI * x) * Math.sin(Math.PI * 2 * y)) * Math.sin(3 * Math.PI * y));
		float y_ = (float)((2 + Math.sin(2 * Math.PI * x) * Math.sin(Math.PI * 2 * y)) * Math.cos(3 * Math.PI * y));
		float z_ = (float)(Math.cos(Math.PI * 2 * x) * Math.sin(Math.PI * y) + 4 * y - 2);
		Vec3D v = new Vec3D(x_, y_, z_);
		return v;
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
		return 1;
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return 1;
		}

	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	}
