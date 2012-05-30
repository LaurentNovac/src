
package ch.supermafia.framework3D.geometry.mesh;

import java.util.Arrays;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

/*
 * creates a parallelepiped voxel
 */
public class VoxelSpace
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public VoxelSpace(int resX, int resY, int resZ)
		{
		this.resX = resX;
		this.resY = resY;
		this.resZ = resZ;
		vertices = new Vec3D[resX][resY][resZ];
		initialColor = new Vec3D();
		build();
		}
	
	public VoxelSpace(int resX, int resY, int resZ, Vec3D initialColor)
		{
		this.resX = resX;
		this.resY = resY;
		this.resZ = resZ;
		this.initialColor = initialColor;
		vertices = new Vec3D[resX][resY][resZ];
		build();
		}
	
	public VoxelSpace(int resX, int resY, int resZ, Vec3D[][][] vertices)
		{
		this.vertices = vertices;
		this.resX = resX;
		this.resY = resY;
		this.resZ = resZ;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public VoxelSpace setColor(int x, int y, int z, Vec3D color)
		{
		return setColor(x, y, z, color.x(), color.y(), color.z());
		}
	
	public VoxelSpace setColor(int x, int y, int z, float r, float g, float b)
		{
		vertices[x][y][z].setX(r);
		vertices[x][y][z].setY(g);
		vertices[x][y][z].setZ(b);
		return this;
		}
	
	public VoxelSpace setColor(VoxelSpace voxelSpace)
		{
		return this;
		}
	
	public Vec3D getColor(int x, int y, int z)
		{
		return vertices[x][y][z];
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("VoxelSpace [resX=");
		builder.append(resX);
		builder.append(", resY=");
		builder.append(resY);
		builder.append(", resZ=");
		builder.append(resZ);
		builder.append(", vertices=");
		builder.append(Arrays.toString(vertices));
		builder.append("]");
		return builder.toString();
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getResX()
		{
		return resX;
		}
	
	public int getResY()
		{
		return resY;
		}
	
	public int getResZ()
		{
		return resZ;
		}
	
	public Vec3D[][][] getVertices()
		{
		return vertices;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void build()
		{
		for(int i = 0; i < resX; i++)
			{
			for(int j = 0; j < resY; j++)
				{
				for(int k = 0; k < resZ; k++)
					{
					vertices[i][j][k] = initialColor.cloneOf();
					}
				}
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private int resX;
	private int resY;
	private int resZ;
	private Vec3D initialColor;
	//tool
	private Vec3D[][][] vertices;
	
	}
