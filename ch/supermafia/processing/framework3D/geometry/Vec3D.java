
package ch.supermafia.processing.framework3D.geometry;

import ch.supermafia.processing.framework3D.mathematics.MathUtilities;

public class Vec3D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Vec3D()
		{
		this.x = 0;
		this.y = 0;
		this.z = 0;
		}
	
	public Vec3D(float x, float y, float z)
		{
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	/**
	 * convenience getter
	 * @return x coordinate
	 */
	public float x()
		{
		return x;
		}
	
	/**
	 * convenience getter
	 * @return y coordinate
	 */
	public float y()
		{
		return y;
		}
	
	/**
	 * convenience getter
	 * @return z coordinate
	 */
	public float z()
		{
		return z;
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
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Vec3D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", z=");
		builder.append(z);
		builder.append(", norm()=");
		builder.append(norm());
		builder.append("]");
		return builder.toString();
		}
	
	public Vec3D add(Vec3D v)
		{
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
		}
	
	public Vec3D sub(Vec3D v)
		{
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
		}
	
	public Vec3D mult(Vec3D v)
		{
		this.x *= v.x;
		this.y *= v.y;
		this.z *= v.z;
		return this;
		}
	
	public Vec3D mult(float m)
		{
		this.scale(m);
		return this;
		}
	
	public Vec3D scale(float sX, float sY, float sZ)
		{
		this.x *= sX;
		this.y *= sY;
		this.z *= sZ;
		return this;
		}
	
	public Vec3D scale(float s)
		{
		this.x *= s;
		this.y *= s;
		this.z *= s;
		return this;
		}
	
	public float dot(Vec3D v)
		{
		return (this.x * v.x + this.y * v.y + this.z * v.z);
		}
	
	public float norm()
		{
		return (float)Math.sqrt(x * x + y * y + z * z);
		}
	
	public Vec3D normalize()
		{
		float magnitude = this.norm();
		this.x /= magnitude;
		this.y /= magnitude;
		this.z /= magnitude;
		return this;
		}
	
	public float angleWith(Vec3D v)
		{
		float n = this.norm() * v.norm();
		float scalarProduct = this.dot(v);
		float angle = (float)Math.acos(scalarProduct / n);
		
		return angle;
		}
	
	public Vec3D projectOn(Vec3D v)
		{
		//TODO
		return null;
		}
	
	public boolean isEqual(Vec3D v, float epsilon)
		{
		boolean bX = MathUtilities.isEqual(this.x, v.x, epsilon);
		boolean bY = MathUtilities.isEqual(this.y, v.y, epsilon);
		boolean bZ = MathUtilities.isEqual(this.z, v.z, epsilon);
		
		return bX && bY && bZ;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private float x;
	private float y;
	private float z;
	
	}
