package ch.supermafia.framework3D.geometry.matrix;


public class Matrix4x4Identity extends Matrix4x4
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Matrix4x4Identity()
		{
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
	protected void fill()
		{
//		for(int y = 0; y < dim; y++)
//			{
//			for(int x = 0; x < dim; x++)
//				{
//				if (x == y)
//					{
//					this.data[index(x, y)] = 1.0f;
//					}
//				else
//					{
//					this.data[index(x, y)] = 0.0f;
//					}
//				}
//			}
		data[index(0, 0)] = 1.0f;
		data[index(0, 1)] = 0.0f;
		data[index(0, 2)] = 0.0f;
		data[index(0, 3)] = 0.0f;
		
		data[index(1, 0)] = 0.0f;
		data[index(1, 1)] = 1.0f;
		data[index(1, 2)] = 0.0f;
		data[index(1, 3)] = 0.0f;
		
		data[index(2, 0)] = 0.0f;
		data[index(2, 1)] = 0.0f;
		data[index(2, 2)] = 1.0f;
		data[index(2, 3)] = 0.0f;
		
		data[index(3, 0)] = 0.0f;
		data[index(3, 1)] = 0.0f;
		data[index(3, 2)] = 0.0f;
		data[index(3, 3)] = 1.0f;
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	

	}
