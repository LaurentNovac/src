
package ch.supermafia.processing.framework3D.Test;

import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4;

public class Matrix4X4Test extends Matrix4x4
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Matrix4X4Test(float[] dataIn)
		{
		fill(dataIn);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void fill(float[] dataIn)
		{
		for(int i = 0; i < dim * dim; i++)
			{
			data[i] = dataIn[i];
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	}
