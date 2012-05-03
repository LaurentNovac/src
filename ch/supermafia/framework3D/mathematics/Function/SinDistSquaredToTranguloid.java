
package ch.supermafia.framework3D.mathematics.Function;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.mathematics.MathUtilities;

public class SinDistSquaredToTranguloid implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public SinDistSquaredToTranguloid()
		{
		lerpParam = 0.0f;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		Vec3D tt = tranguloid(x, y);
		Vec3D t = sinDistSquared(x, y);
		float x_ = MathUtilities.linearInterpolate(t.x(), tt.x(), lerpParam);
		float y_ = MathUtilities.linearInterpolate(t.y(), tt.y(), lerpParam);
		float z_ = MathUtilities.linearInterpolate(t.z(), tt.z(), lerpParam);
		
		return new Vec3D(x_, y_, z_);
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
		return 10;//(float)(2 * Math.PI);
		}
	
	@Override
	public float getVmin()
		{
		return -10;
		}
	
	@Override
	public float getVMax()
		{
		return 10;//(float)(2 * Math.PI);
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setLerpParam(float lerpParam)
		{
		if (lerpParam > 1.0f)
			{
			this.lerpParam = 1.0f;
			}
		else if (lerpParam < 0.0f)
			{
			this.lerpParam = 0.0f;
			}
		else
			{
			this.lerpParam = lerpParam;
			
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D tranguloid(float u, float v)
		{
		// TODO Auto-generated method stub
		float x = (float)(2 * sin(3 * u) / (2 + cos(v)));
		float y = (float)(2 * (sin(u) + 2 * sin(2 * u)) / (2 + cos(v + 2 * PI / 3)));
		float z = (float)((cos(u) - 2 * cos(2 * u)) * (2 + cos(v)) * (2 + cos(v + 2 * PI / 3)) / 4);
		return (new Vec3D(x, y, z));
		}
	
	private Vec3D sinDistSquared(float x, float y)
		{
		return new Vec3D(x, y, (float)Math.sin(Math.sqrt(x * x + y * y)));
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float lerpParam;
	}
