
package ch.supermafia.processing.framework3D.geometry.vector;

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
	public Vec4D(Vec3D vec)
		{
		this.vec = vec;
		this.x = vec.x();
		this.y = vec.y();
		this.z = vec.x();
		w = 1;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	Vec3D toCartesian()
		{
		vec.setX(x / w);
		vec.setY(y / w);
		vec.setZ(z / w);
		return vec;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
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
