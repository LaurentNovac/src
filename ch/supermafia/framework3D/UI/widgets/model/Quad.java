
package ch.supermafia.framework3D.UI.widgets.model;

import ch.supermafia.framework3D.UI.model.Widget;
import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class Quad extends Widget
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Quad()
		{
		vertices = new Vec3D[4];
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setVertices(Vec3D[] vertices)
		{
		this.vertices = vertices;
		}
	
	public Quad setTopLeft(Vec3D v)
		{
		vertices[0] = v;
		return this;
		}
	
	public Quad setBottomLeft(Vec3D v)
		{
		vertices[1] = v;
		return this;
		}
	
	public Quad setBottomRight(Vec3D v)
		{
		vertices[2] = v;
		return this;
		}
	
	public Quad setTopRight(Vec3D v)
		{
		vertices[3] = v;
		return this;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public Vec3D[] getVertices()
		{
		return vertices;
		}
	
	public Vec3D getTopLeft()
		{
		return vertices[0];
		}
	
	public Vec3D getBottomLeft()
		{
		return vertices[1];
		}
	
	public Vec3D getBottomRight()
		{
		return vertices[2];
		}
	
	public Vec3D getTopRight()
		{
		return vertices[3];
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private Vec3D[] vertices;
	}
