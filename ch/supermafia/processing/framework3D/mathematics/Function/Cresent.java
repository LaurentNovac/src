package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;


public class Cresent implements FunctionR2R3_I
	{

	@Override
	public Vec3D f(float x, float y)
		{
		float x_=(float)((2+Math.sin(2*Math.PI*x)*Math.sin(Math.PI*2*y))*Math.sin(3*Math.PI*y));
		float y_=(float)((2+Math.sin(2*Math.PI*x)*Math.sin(Math.PI*2*y))*Math.cos(3*Math.PI*y));
		float z_=(float)(Math.cos(Math.PI*2*x)*Math.sin(Math.PI*y)+4*y-2);
		return new Vec3D(x_, y_, z_);
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
	
	}
//x = (2 + sin(2 pi u) sin(2 pi v)) sin(3 pi v)
//y = (2 + sin(2 pi u) sin(2 pi v)) cos(3 pi v)
//
//z = cos(2 pi u) sin(2 pi v) + 4 v - 2