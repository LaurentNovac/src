
package ch.supermafia.framework3D.geometry.mesh.polygonizer;

import java.util.Arrays;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class GridCell
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public GridCell(Vec3D origin, float edgeSize)
		{
		this.origin = origin;
		this.edgeSize = edgeSize;
		fill();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("GridCell [origin=");
		builder.append(origin);
		builder.append(", edgeSize=");
		builder.append(edgeSize);
		builder.append(", p=");
		builder.append(Arrays.toString(p));
		builder.append(", values=");
		builder.append(Arrays.toString(values));
		builder.append("]");
		return builder.toString();
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public Vec3D[] getP()
		{
		return p;
		}
	
	public float[] getValues()
		{
		return values;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void fill()
		{
		p = new Vec3D[8];
		p[0] = new Vec3D(origin);
		p[1] = new Vec3D(origin.x() + edgeSize, origin.y(), origin.z());
		p[2] = new Vec3D(origin.x() + edgeSize, origin.y(), origin.z() + edgeSize);
		p[3] = new Vec3D(origin.x(), origin.y(), origin.z() + edgeSize);
		
		p[4] = new Vec3D(origin.x(), origin.y() + edgeSize, origin.z());
		p[5] = new Vec3D(origin.x() + edgeSize, origin.y() + edgeSize, origin.z());
		p[6] = new Vec3D(origin.x() + edgeSize, origin.y() + edgeSize, origin.z() + edgeSize);
		p[7] = new Vec3D(origin.x(), origin.y() + edgeSize, origin.z() + edgeSize);
		
		values = new float[8];//probably not really necessary, seems pretty implicit
		for(int i = 0; i < 8; i++)
			{
			values[i] = i;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private Vec3D origin;//rear bottom left vertex
	private float edgeSize;
	//tools
	private Vec3D[] p;
	private float[] values;
	}
