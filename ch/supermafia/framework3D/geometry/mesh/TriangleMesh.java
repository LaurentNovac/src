
package ch.supermafia.framework3D.geometry.mesh;

import java.util.ArrayList;
import java.util.List;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

public class TriangleMesh implements Mesh3D_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public TriangleMesh()
		{
		triangles = new ArrayList<Vec3D>();
		normals = new ArrayList<Vec3D>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("TriangleMesh [triangles=");
		builder.append(triangles);
		builder.append(", normals=");
		builder.append(normals);
		builder.append("]");
		return builder.toString();
		}
	
	@Override
	public Mesh3D_I applyIdentity()
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	@Override
	public Mesh3D_I translate(Vec3D transVec)
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	@Override
	public Mesh3D_I scale(float s)
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	@Override
	public Mesh3D_I rotateX(float angle)
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	@Override
	public Mesh3D_I rotateY(float angle)
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	@Override
	public Mesh3D_I rotateZ(float angle)
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	public Mesh3D_I addTriangleFace(Vec3D v1, Vec3D v2, Vec3D v3)
		{
		return null;
		}
	
	@Override
	public Mesh3D_I polygonize()
		{
		// TODO Auto-generated method stub
		return null;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public List<Vec3D> getTriangles()
		{
		return triangles;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private List<Vec3D> triangles;
	private List<Vec3D> normals;
	
	}
