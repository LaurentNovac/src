
package ch.supermafia.framework3D.geometry.mesh;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class CubicZone implements Zone_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public CubicZone(Vec3D topLeft, float edgeLength)
		{
		this.topLeft = topLeft;
		this.edgeLength = edgeLength;
		vertices = new Vec3D[8];
		vertices[0] = topLeft;
		vertices[1] = new Vec3D(topLeft.x() + edgeLength, topLeft.y(), topLeft.z());
		vertices[2] = new Vec3D(topLeft.x() + edgeLength, topLeft.y() + edgeLength, topLeft.z());
		vertices[3] = new Vec3D(topLeft.x(), topLeft.y() + edgeLength, topLeft.z());
		
		vertices[4] = new Vec3D(topLeft.x(), topLeft.y(), topLeft.z() + edgeLength);
		vertices[5] = new Vec3D(topLeft.x(), topLeft.y() + edgeLength, topLeft.z() + edgeLength);
		vertices[6] = new Vec3D(topLeft.x() + edgeLength, topLeft.y() + edgeLength, topLeft.z() + edgeLength);
		vertices[7] = new Vec3D(topLeft.x() + edgeLength, topLeft.y(), topLeft.z() + edgeLength);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public boolean intersects(Vec3D vec)
		{
		boolean isInRes = true;
		isInRes &= vec.x() >= topLeft.x();
		isInRes &= vec.x() <= topLeft.x() + edgeLength;
		isInRes &= vec.y() >= topLeft.y();
		isInRes &= vec.y() <= topLeft.y() + edgeLength;
		isInRes &= vec.z() >= topLeft.z();
		isInRes &= vec.z() <= topLeft.z() + edgeLength;
		return isInRes;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public Vec3D[] getVertices()
		{
		return vertices;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private Vec3D topLeft;//top left of front face
	private float edgeLength;
	//tool
	private Vec3D[] vertices;
	}
