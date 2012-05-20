
package ch.supermafia.framework3D.geometry.vector;

/**
 * 
 * @author Laurent Novac
 *A wrapper for Vec3D in homogenuous plane
 */
public class Vec4D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Vec4D(float x, float y, float z, float w)
		{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		}
	
	public Vec4D(Vec3D vec)
		{
		this(vec.x(), vec.y(), vec.z(), 1.0f);
		this.vec = vec;
		}
	
	public Vec4D(Vec4D vec)
		{
		this(vec.toCartesian());
		}
	
	public Vec4D()
		{
		this(0.0f, 0.0f, 0.0f, 0.0f);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public Vec3D toCartesian()
		{
		vec.setX(x / w);
		vec.setY(y / w);
		vec.setZ(z / w);
		return vec;
		}
	
	public Vec4D cloneOf()
		{
		return new Vec4D(this);
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Vec4D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append(", w=");
		builder.append(w);
		builder.append("]");
		return builder.toString();
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setX(float x)
		{
		this.x = x;
		}
	
	public void setY(float y)
		{
		this.y = y;
		}
	
	public void setZ(float z)
		{
		this.z = z;
		}
	
	public void setW(float w)
		{
		this.w = w;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public float getX()
		{
		return x;
		}
	
	public float getY()
		{
		return y;
		}
	
	public float getZ()
		{
		return z;
		}
	
	public float getW()
		{
		return w;
		}
	
	public float x()
		{
		return x;
		}
	
	public float y()
		{
		return y;
		}
	
	public float z()
		{
		return z;
		}
	
	public float w()
		{
		return w;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float x;
	private float y;
	private float z;
	private float w;
	//input
	private Vec3D vec;
	
	}
