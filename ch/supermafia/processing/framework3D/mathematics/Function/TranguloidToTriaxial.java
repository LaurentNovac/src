
package ch.supermafia.processing.framework3D.mathematics.Function;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;

public class TranguloidToTriaxial implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public TranguloidToTriaxial()
		{
		lerpParam = 0.0f;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		Vec3D t = tranguloid(x, y);
		Vec3D tt = triaxial(x, y);
		
		float x_ = MathUtilities.linearInterpolate(t.x(), tt.x(), lerpParam);
		float y_ = MathUtilities.linearInterpolate(t.y(), tt.y(), lerpParam);
		float z_ = MathUtilities.linearInterpolate(t.z(), tt.z(), lerpParam);
//		float x_ = MathUtilities.cosineInterpolate(t.x(), tt.x(), lerpParam);
//		float y_ = MathUtilities.cosineInterpolate(t.y(), tt.y(), lerpParam);
//		float z_ = MathUtilities.cosineInterpolate(t.z(), tt.z(), lerpParam);
		return new Vec3D(x_, y_, z_);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D tranguloid(float u, float v)
		{
		float x = (float)(2 * sin(3 * u) / (2 + cos(v)));
		float y = (float)(2 * (sin(u) + 2 * sin(2 * u)) / (2 + cos(v + 2 * PI / 3)));
		float z = (float)((cos(u) - 2 * cos(2 * u)) * (2 + cos(v)) * (2 + cos(v + 2 * PI / 3)) / 4);
		return (new Vec3D(x, y, z));
		}
	
	private Vec3D triaxial(float x, float y)
		{
		float pp;
		
		pp = (float)(Math.sqrt(x * x + y * y) / Math.sqrt(2 * Math.PI * Math.PI));
		
		float x_ = (float)((1 - pp) * Math.cos(x) * Math.cos(y) + pp * Math.sin(x) * Math.sin(y));
		float y_ = (float)((1 - pp) * Math.cos(x + Math.PI * 2 / 3) * Math.cos(y + Math.PI * 2 / 3) + pp * Math.sin(x + Math.PI * 2 / 3) * Math.sin(y + Math.PI * 2 / 3));
		float z_ = (float)((1 - pp) * Math.cos(x + 4 * Math.PI / 3) * Math.cos(y + 4 * Math.PI / 3) + pp * Math.sin(x + 4 * Math.PI / 3) * Math.sin(y + 4 * Math.PI / 3));
		
		return new Vec3D(x_, y_, z_);
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
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return 0.0f;
		}
	
	@Override
	public float getUMax()
		{
		return (float)(2 * Math.PI);
		}
	
	@Override
	public float getVmin()
		{
		return 0.0f;
		}
	
	@Override
	public float getVMax()
		{
		return (float)(2 * Math.PI);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float lerpParam;
	
	}
