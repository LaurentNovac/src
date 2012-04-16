
package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;

public class KleinCycloid implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public KleinCycloid(float a, float b, float c)
		{
		this.a = a;
		this.b = b;
		this.c = c;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		float x_ = (float)(Math.cos(x / c) * Math.cos(x / b) * (a + Math.cos(y)) + Math.sin(x / b) * Math.sin(y) * Math.cos(y));
		float y_ = (float)(Math.sin(x / c) * Math.cos(x / b) * (a + Math.cos(y)) + Math.sin(x / b) * Math.sin(y) * Math.cos(y));
		float z_ = (float)(-Math.sin(x / b) * (a + Math.cos(y)) + Math.cos(x / b) * Math.sin(y) * Math.cos(y));
		return new Vec3D(x_, y_, z_);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float a;
	private float b;
	private float c;
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
	
	}
