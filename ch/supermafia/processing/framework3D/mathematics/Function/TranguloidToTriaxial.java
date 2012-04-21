
package ch.supermafia.processing.framework3D.mathematics.Function;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import processing.core.PApplet;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class TranguloidToTriaxial implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		Vec3D t = tranguloid(x, y);
		Vec3D tt = triaxial(x, y);
		float x_ = PApplet.lerp(t.x(), tt.x(), 0.5f);
		float y_ = PApplet.lerp(t.y(), tt.y(), 0.5f);
		float z_ = PApplet.lerp(t.z(), tt.z(), 0.5f);
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
		return (float)(2 * Math.PI);
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
	
	}
