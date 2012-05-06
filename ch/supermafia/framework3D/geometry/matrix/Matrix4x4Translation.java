
package ch.supermafia.framework3D.geometry.matrix;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Matrix4x4Translation extends Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Matrix4x4Translation(Vec3D transVec)
		{
		this.transVec = transVec;
		fill();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void fill()
		{
		data[index(0, 0)] = 1.0f;
		data[index(0, 1)] = 0.0f;
		data[index(0, 2)] = 0.0f;
		data[index(0, 3)] = transVec.x();
		
		data[index(1, 0)] = 0.0f;
		data[index(1, 1)] = 1.0f;
		data[index(1, 2)] = 0.0f;
		data[index(1, 3)] = transVec.y();
		
		data[index(2, 0)] = 0.0f;
		data[index(2, 1)] = 0.0f;
		data[index(2, 2)] = 1.0f;
		data[index(2, 3)] = transVec.z();
		
		data[index(3, 0)] = 0.0f;
		data[index(3, 1)] = 0.0f;
		data[index(3, 2)] = 0.0f;
		data[index(3, 3)] = 1.0f;
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Vec3D transVec;
	
	}
