
package ch.supermafia.framework3D.geometry.matrix;

import java.util.Arrays;

import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.geometry.vector.Vec4D;

/**
 * 
 *<pre>
 * This represents the "default matrix" it is filled with zeros, 
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
		}
	
	public Matrix4x4(Matrix4x4 src)
		{
		this();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
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
	
	private Matrix4x4 cloneOf()
		{
		return new Matrix4x4();
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
	
	public Vec3D transformVec(Vec3D vec)
		{
		
		Vec4D vecH = new Vec4D(vec);
		Vec4D res = vecH.cloneOf();
		res.setX(scalar(vecH, 0));
		res.setY(scalar(vecH, 1));
		res.setZ(scalar(vecH, 2));
		res.setW(scalar(vecH, 3));
		
		return res.toCartesian();
		}
	
	public boolean isEqual(Matrix4x4 mat, float epsilon)
		{
		for(int i = 0; i < dim * dim; i++)
			{
			if ((data[i] - mat.data[i]) > epsilon) { return false; }
			}
		return true;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void scalar(int lineI, int columnJ, Matrix4x4 mat, Matrix4x4 res)
		{
		for(int i = 0; i < dim; i++)
			{
			res.data[index(columnJ, lineI)] += this.data[index(i, lineI)] * mat.data[index(columnJ, i)];
			}
		}
	
	private float scalar(Vec4D res, int lineI)
		{
		float s = 0.0f;
		s += this.data[index(0, lineI)] * res.getX();
		s += this.data[index(1, lineI)] * res.getY();
		s += this.data[index(2, lineI)] * res.getZ();
		s += this.data[index(3, lineI)] * res.getW();
		return s;
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
