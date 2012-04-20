
package ch.supermafia.processing.framework3D.geometry.vector;

import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4;
import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4Identity;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;

public class Vec3D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Vec3D(float x, float y, float z)
		{
		this.x = x;
		this.y = y;
		this.z = z;
		this.transMatrix = new Matrix4x4Identity();
		}
	
	public Vec3D()
		{
		this(0, 0, 0);
		}
	
	public Vec3D(Vec3D src)
		{
		this(src.x, src.y, src.z);
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
	
	public Vec3D cloneOf()
		{
		return new Vec3D(this);
		}
	
	public Vec3D add(Vec3D v)
		{
		//TODO use translate Matrix4x4
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		return this;
		}
	
	public Vec3D sub(Vec3D v)
		{
		//TODO call translate
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		return this;
		}
	
	public Vec3D mult(Vec3D v)
		{
		//TODO call scale
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
	
	/**
	 * 
	 * @param sX
	 * @param sY
	 * @param sZ
	 * @return Vec3D
	 */
	public Vec3D scale(float sX, float sY, float sZ)
		{
		//TODO use Matrix4x4
		this.x *= sX;
		this.y *= sY;
		this.z *= sZ;
		return this;
		}
	
	public Vec3D scale(Vec3D vec)
		{
		//TODO 
		return null;
		}
	
	/**
	 * 
	 * @param s
	 * @return the Vec3D scaled in place 
	 */
	public Vec3D scale(float s)
		{
		//TODO use Matrix4x4
		this.x *= s;
		this.y *= s;
		this.z *= s;
		return this;
		}
	
	public Vec3D rotate(float angle)
		{
		//TODO
		return this;
		}
	
	public Vec3D rotate(float angle, Vec3D center)
		{
		//TODO
		return this;
		}
	
	public float dot(Vec3D v)
		{
		return (this.x * v.x + this.y * v.y + this.z * v.z);
		}
	
	/**
	 * @param v
	 * @return a new Vec3D
	 */
	public Vec3D cross(Vec3D v)
		{
		Vec3D vec = new Vec3D();
		vec.x = (this.y * v.z) - (this.z * v.y);
		vec.y = (this.z * v.x) - (this.x * v.z);
		vec.z = (this.x * v.y) - (this.y * v.x);
		return vec;
		}
	
	public float norm()
		{
		return (float)Math.sqrt(x * x + y * y + z * z);
		}
	
	/**
	 * 
	 * @return Vec3D normalized in placed
	 */
	public Vec3D normalize()
		{
		float magnitude = this.norm();
		this.x /= magnitude;
		this.y /= magnitude;
		this.z /= magnitude;
		return this;
		}
	
	/**
	 * 
	 * @return a new Vec3D normalized
	 */
	public Vec3D normalize2()
		{
		float magnitude = this.norm();
		float x_ = this.x / magnitude;
		float y_ = this.y / magnitude;
		float z_ = this.z / magnitude;
		return new Vec3D(x_, y_, z_);
		}
	
	public float angleWith(Vec3D v)
		{
		float n = this.norm() * v.norm();
		float scalarProduct = this.dot(v);
		float angle = (float)Math.acos(scalarProduct / n);
		
		return angle;
		}
	
	/**
	 * @param v
	 * @return Vec3D projected on v in place
	 */
	public Vec3D projectOn(Vec3D v)
		{
		//TODO use Matrix4x4
		float projNorm = this.dot(v) / v.norm();
		Vec3D vUnit = v.normalize2();
		vUnit.mult(projNorm);
		return vUnit;
		}
	
	public boolean isEqual(Vec3D v, float epsilon)
		{
		boolean bX = MathUtilities.isEqual(this.x, v.x, epsilon);
		boolean bY = MathUtilities.isEqual(this.y, v.y, epsilon);
		boolean bZ = MathUtilities.isEqual(this.z, v.z, epsilon);
		
		return bX && bY && bZ;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D transform()
		{
		//TODO
		return this;
		}
	
	private Vec4D multiplyLeftByTransMat()
		{
		//TODO
		return null;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private float x;
	private float y;
	private float z;
	private Matrix4x4 transMatrix;
	}
