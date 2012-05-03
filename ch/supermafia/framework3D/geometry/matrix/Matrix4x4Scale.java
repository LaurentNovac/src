
package ch.supermafia.framework3D.geometry.matrix;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Matrix4x4Scale extends Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Matrix4x4Scale(Vec3D scaleVec)
		{
		this.scaleVec = scaleVec;
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
		for(int y = 0; y < dim; y++)
			{
			for(int x = 0; x < dim; x++)
				{
				if (x == y)
					{
					//nothing
					}
				else data[index(x, y)] = 0;
				}
			}
		data[index(0, 0)] = scaleVec.x();
		data[index(1, 1)] = scaleVec.y();
		data[index(2, 2)] = scaleVec.z();
		data[index(3, 3)] = 1;
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D scaleVec;
	
	}
