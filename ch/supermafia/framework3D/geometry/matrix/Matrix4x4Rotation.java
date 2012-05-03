
package ch.supermafia.framework3D.geometry.matrix;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Matrix4x4Rotation extends Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Matrix4x4Rotation(Vec3D centerVec, float angle)
		{
		this.centerVec = centerVec;
		this.angle = angle;
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
		float x = centerVec.x();
		float y = centerVec.y();
		float z = centerVec.z();
		float sinA = (float)Math.sin(angle);
		float cosA = (float)Math.cos(angle);
		data[index(0, 0)] = x * x * (1 - cosA) + cosA;
		data[index(0, 1)] = x * y * (1 - cosA) - z * sinA;
		data[index(0, 2)] = x * z * (1 - cosA) + y * sinA;
		data[index(0, 3)] = 0.0f;
		
		data[index(1, 0)] = y * x * (1 - cosA) + z * sinA;
		data[index(1, 1)] = y * y * (1 - cosA) + cosA;
		data[index(1, 2)] = y * z * (1 - cosA) - x * sinA;
		data[index(1, 3)] = 0.0f;
		
		data[index(2, 0)] = x * z * (1 - cosA) - y * sinA;
		data[index(2, 1)] = y * z * (1 - cosA) + x * sinA;
		data[index(2, 2)] = z * z * (1 - cosA) + cosA;
		data[index(2, 3)] = 0.0f;
		
		data[index(3, 0)] = 0;
		data[index(3, 1)] = 0;
		data[index(3, 2)] = 0;
		data[index(3, 3)] = 1.0f;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D centerVec;
	private float angle;
	}
