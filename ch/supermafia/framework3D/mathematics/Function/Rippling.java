
package ch.supermafia.framework3D.mathematics.Function;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Rippling implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Rippling(float t,float dim)
		{
		this.t = t;
		this.dim=dim;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float dim_=dim;
		float f_x=x-(dim_/2.0f);
		float f_y=y-(dim_/2.0f);
		float d=(float)Math.sqrt(f_x*f_x+f_y*f_y);
		
		float d1=(float)Math.cos((d/10.0f)-(t/7.0f));
		float d2=(d/10.0f)+1.0f;
		float f_z=128.0f+127.0f*(d1/d2);

		return new Vec3D(x, y,f_z);	
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
		return 600;
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return 600;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	
	public void setT(float t)
		{
		this.t = t;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float t;
	private float dim;
	}
