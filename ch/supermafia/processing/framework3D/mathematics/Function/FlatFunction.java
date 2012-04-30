
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class FlatFunction implements FunctionR2R3_I
	{
	
	@Override
	public Vec3D f(float x, float y)
		{
		return new Vec3D(x, y, 0);
		}
	
	@Override
	public float getUmin()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	@Override
	public float getUMax()
		{
		// TODO Auto-generated method stub
		return 100;
		}
	
	@Override
	public float getVmin()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		// TODO Auto-generated method stub
		return 100;
		}
	
	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	
	}
