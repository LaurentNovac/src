
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class Triaxial implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float pp;
		
		pp = (float)(Math.sqrt(x * x + y * y) / Math.sqrt(2 * Math.PI * Math.PI));
		
		float x_ = (float)((1 - pp) * Math.cos(x) * Math.cos(y) + pp * Math.sin(x) * Math.sin(y));
		float y_ = (float)((1 - pp) * Math.cos(x + Math.PI * 2 / 3) * Math.cos(y + Math.PI * 2 / 3) + pp * Math.sin(x + Math.PI * 2 / 3) * Math.sin(y + Math.PI * 2 / 3));
		float z_ = (float)((1 - pp) * Math.cos(x + 4 * Math.PI / 3) * Math.cos(y + 4 * Math.PI / 3) + pp * Math.sin(x + 4 * Math.PI / 3) * Math.sin(y + 4 * Math.PI / 3));
		
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
		return (float)(2*Math.PI);
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return (float)(2 * Math.PI);
		}

	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	
	}
