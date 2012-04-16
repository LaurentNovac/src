
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;

public class SinDistSquared implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public SinDistSquared()
		{
		}
	
	@Override
	public Vec3D f(float x, float y)
		{
		return new Vec3D(x, y, (float)Math.sin(Math.sqrt(x * x + y * y)));
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
		return 0;
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
		return 0;
		}
	
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	}
