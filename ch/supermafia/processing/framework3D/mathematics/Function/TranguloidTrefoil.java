
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.PI;

public class TranguloidTrefoil implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float u, float v)
		{
		float x = (float)(2 * sin(3 * u) / (2 + cos(v)));
		float y = (float)(2 * (sin(u) + 2 * sin(2 * u)) / (2 + cos(v + 2 * PI / 3)));
		float z = (float)((cos(u) - 2 * cos(2 * u)) * (2 + cos(v)) * (2 + cos(v + 2 * PI / 3)) / 4);
		return (new Vec3D(x, y, z));
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return (float)-Math.PI;
		}
	
	@Override
	public float getUMax()
		{
		return (float)Math.PI;
		}
	
	@Override
	public float getVmin()
		{
		return (float)-Math.PI;
		}
	
	@Override
	public float getVMax()
		{
		return (float)Math.PI;
		}

	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	}
