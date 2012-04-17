
package ch.supermafia.processing.framework3D.geometry;

public class MatrixT
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public MatrixT()
		{
		dim = 4;
		data = new float[dim * dim];//4X4 homogenuous matrix
		this.identity();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public MatrixT rotate(float angle)
		{
		//TODO
		return this;
		}
	
	public MatrixT rotate(float angle, Vec3D center)
		{
		//TODO
		return this;
		}
	
	public MatrixT translate(Vec3D trans)
		{
		//TODO
		return this;
		}
	
	public MatrixT scale(float s)
		{
		//TODO
		return this;
		}
	
	public MatrixT scale(Vec3D s)
		{
		//TODO
		return this;
		}
	
	public MatrixT identity()
		{
		for(int y = 0; y < dim; y++)
			{
			for(int x = 0; x < dim; x++)
				{
				if (x == y)
					{
					this.data[index(x, y)] = 1.0f;
					}
				else
					{
					this.data[index(x, y)] = 0.0f;
					}
				}
			}
		return this;
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
	private int index(int x, int y)
		{
		return dim * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float[] data;
	private int dim;
	
	}
