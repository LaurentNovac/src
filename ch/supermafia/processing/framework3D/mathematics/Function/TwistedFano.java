package ch.supermafia.processing.framework3D.mathematics.Function;

import ch.supermafia.processing.framework3D.geometry.Vec3D;


public class TwistedFano implements FunctionR2R3_I
	{

	@Override
	public Vec3D f(float x, float y)
		{
		float tp = (float)(Math.PI*2/3.0f);
		float a = (float)(Math.PI*2*(x-y/3));
		float b=(float)(Math.PI*2*(-x-y/3));
		float c=(float)(Math.PI*2*(-x-y));
		float d=(float)(Math.PI*2*(-x+y));
		float x_=(float)(Math.cos(a) * Math.cos(b) * Math.cos(Math.PI*2*y) * Math.cos(Math.PI*2*x));
		float y_=(float)(Math.cos(a+tp)*Math.cos(b+tp)*Math.cos(c+tp)*Math.cos(Math.PI*2*(x-1)));
		float z_=(float)(Math.cos(a-tp)*Math.cos(b-tp)*Math.cos(d+tp)*Math.cos(Math.PI*2*(x-2)));
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
//XYZ Eval(double t,double p)
//{
//   double a,b,c,d,tp;
//   XYZ q;
//
//   tp = TWOPI * 2 / 3.0;
//   a = TWOPI * (t - p/3);
//   b = TWOPI * (-t - p/3);
//   c = TWOPI*(-t-p);
//   d = TWOPI*(-t+p);
//   q.x = cos(a) * cos(b) * cos(TWOPI*p) * cos(TWOPI*t);
//   q.y = cos(a+tp)*cos(b+tp)*cos(c+tp)*cos(TWOPI*(t-1));
//   q.z = cos(a-tp)*cos(b-tp)*cos(d+tp)*cos(TWOPI*(t-2));
//
//   return(q);
//}