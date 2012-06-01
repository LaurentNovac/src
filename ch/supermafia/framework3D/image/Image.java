
package ch.supermafia.framework3D.image;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Image
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Image(int width, int height)
		{
		this.data = new Vec3D[width * height];
		this.width = width;
		this.height = height;
		}
	
	public Image(int width, int height, Vec3D[] data)
		{
		this.data = data;
		this.width = width;
		this.height = height;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public Vec3D getColor(int x, int y)
		{
		return data[index(x, y)];
		}
	
	public Image setColor(int x, int y, Vec3D color)
		{
		data[index(x, y)].setX(color.x());
		data[index(x, y)].setY(color.y());
		data[index(x, y)].setY(color.z());
		return this;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setData(Vec3D[] data)
		{
		this.data = data;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getWidth()
		{
		return width;
		}
	
	public int getHeight()
		{
		return height;
		}
	
	public Vec3D[] getData()
		{
		return data;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private int index(int x, int y)
		{
		return x + width * y;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private Vec3D[] data;
	private int width;
	private int height;
	}
