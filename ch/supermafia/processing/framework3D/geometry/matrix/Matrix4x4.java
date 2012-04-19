
package ch.supermafia.processing.framework3D.geometry.matrix;

import java.util.Arrays;

import ch.supermafia.processing.framework3D.geometry.vector.Vec4D;

/**
 * 
 *<pre>
 * This represents the "default matrix" the identy, 
 * subclasses represents transformation matrix
 * such as Rotation or Translation
 * </pre>
 * @author Laurent Novac
 *
 */
public class Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Matrix4x4()
		{
		dim = 4;
		data = new float[dim * dim];//4X4 homogenuous matrix
		this.fill();
		}
	
	public Matrix4x4(Matrix4x4 src)
		{
		this();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public Matrix4x4 cloneOf()
		{
		return new Matrix4x4(this);
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("Matrix4x4 [data=");
		builder.append(Arrays.toString(data));
		builder.append(", dim=");
		builder.append(dim);
		builder.append("]");
		return builder.toString();
		}
	
	public Matrix4x4 composeTrans(Matrix4x4 matrix)
		{
		return multRight(matrix);
		}
	
	public Matrix4x4 multRight(Matrix4x4 mat)
		{
		Matrix4x4 res = this.cloneOf();
		
		for(int j = 0; j < dim; j++)
			{
			for(int i = 0; i < dim; i++)
				{
				scalar(i, j, mat, res);
				}
			}
		
		this.data = res.data;
		return this;
		}
	
	private void scalar(int lineI, int columnJ, Matrix4x4 mat, Matrix4x4 res)
		{
		for(int i = 0; i < dim; i++)
			{
			res.data[index(columnJ, lineI)] += this.data[index(i, lineI)] * mat.data[index(columnJ, i)];
			}
		}
	
	public boolean isEqual(Matrix4x4 mat, float epsilon)
		{
		for(int i = 0; i < dim * dim; i++)
			{
			if ((data[i] - mat.data[i]) >= epsilon) { return false; }
			}
		return true;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	protected void fill()
		{
		for(int i = 0; i < dim; i++)
			{
			data[i] = 0;
			}
		}
	
	protected int index(int x, int y)
		{
		return dim * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	protected float[] data;
	protected int dim;
	
	}
