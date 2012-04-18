
package ch.supermafia.processing.framework3D.geometry.matrix;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class Matrix4x4Translation extends Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
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
	@Override
	protected void fill()
		{
		data[index(0, 0)] = 1;
		data[index(0, 1)] = 0;
		data[index(0, 2)] = 0;
		data[index(0, 3)] = 0;
		
		data[index(1, 0)] = 0;
		data[index(1, 1)] = 1;
		data[index(1, 2)] = 0;
		data[index(1, 3)] = 0;
		
		data[index(2, 0)] = 0;
		data[index(2, 1)] = 0;
		data[index(2, 2)] = 1;
		data[index(2, 3)] = 0;
		
		data[index(3, 0)] = transVec.x();
		data[index(3, 1)] = transVec.y();
		data[index(3, 2)] = transVec.z();
		data[index(3, 3)] = 1;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Vec3D transVec;
	
	}
