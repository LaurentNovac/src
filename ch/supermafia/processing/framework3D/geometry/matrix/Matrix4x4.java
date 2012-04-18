
package ch.supermafia.processing.framework3D.geometry.matrix;

import java.util.Arrays;

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
public abstract class Matrix4x4
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
	
	public Matrix4x4 composeTrans(Matrix4x4 matrix)
		{
		//TODO 
		return null;
		}
	
	public Matrix4x4 multRight()
		{
		//TODO
		return null;
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	protected abstract void fill();
	
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
