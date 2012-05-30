
package ch.supermafia.framework3D.geometry.mesh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.mathematics.MathUtilities;
import ch.supermafia.framework3D.mathematics.Function.FunctionR2R3_I;

public class PointCloud implements Mesh3D_I ,Iterable<Vec3D>
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public PointCloud()
		{
		pointCloudList = new ArrayList<Vec3D>();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Mesh3D_I applyIdentity()
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().applyIdentity();
			}
		return this;
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("PointCloud [pointCloudList=");
		builder.append(pointCloudList);
		builder.append("]");
		return builder.toString();
		}
	
	@Override
	public Mesh3D_I translate(Vec3D transVec)
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().translate(transVec);
			}
		return this;
		}
	
	@Override
	public Mesh3D_I scale(float s)
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().scale(s);
			}
		return this;
		}
	
	@Override
	public Mesh3D_I rotateX(float angle)
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().rotateX(angle);
			}
		return this;
		}
	
	@Override
	public Mesh3D_I rotateY(float angle)
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().rotateY(angle);
			}
		return this;
		}
	
	@Override
	public Mesh3D_I rotateZ(float angle)
		{
		Iterator<Vec3D> it = iterator();
		while(it.hasNext())
			{
			it.next().rotateZ(angle);
			}
		return this;
		}
	
	public PointCloud applyFunction(FunctionR2R3_I function)//TODO make parallel
		{
		float minX = computeMinX();
		float maxX = computeMaxX();
		float minY = computeMinY();
		float maxY = computeMaxY();
		for(int i = 0; i < pointCloudList.size(); i++)
			{
			float u = MathUtilities.map(pointCloudList.get(i).x(), minX, maxX, function.getUmin(), function.getUMax());
			float v = MathUtilities.map(pointCloudList.get(i).y(), minY, maxY, function.getVmin(), function.getVMax());
			Vec3D vec = function.f(u, v);
			pointCloudList.get(i).setX(vec.x());
			pointCloudList.get(i).setY(vec.y());
			pointCloudList.get(i).setZ(vec.z());
			}
		return this;
		}
	
	@Override
	public Iterator<Vec3D> iterator()
		{
		return pointCloudList.iterator();
		}
	
	public PointCloud addPoint(Vec3D point)
		{
		pointCloudList.add(point);
		return this;
		}
	
	public PointCloud removePoint(Vec3D point)
		{
		pointCloudList.remove(point);
		return this;
		}
	
	public PointCloud clear()
		{
		pointCloudList.clear();
		return this;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public List<Vec3D> getPointCloudList()
		{
		return pointCloudList;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private float computeMinX()
		{
		List<Float> listX = new ArrayList<Float>();
		for(Vec3D p:pointCloudList)
			{
			listX.add(p.x());
			}
		Collections.sort(listX);
		return listX.get(0);
		}
	
	private float computeMaxX()
		{
		List<Float> listX = new ArrayList<Float>();
		for(Vec3D p:pointCloudList)
			{
			listX.add(p.x());
			}
		Collections.sort(listX);
		return listX.get(listX.size() - 1);
		}
	
	private float computeMinY()
		{
		List<Float> listY = new ArrayList<Float>();
		for(Vec3D p:pointCloudList)
			{
			listY.add(p.y());
			}
		Collections.sort(listY);
		return listY.get(0);
		}
	
	private float computeMaxY()
		{
		List<Float> listY = new ArrayList<Float>();
		for(Vec3D p:pointCloudList)
			{
			listY.add(p.y());
			}
		Collections.sort(listY);
		return listY.get(listY.size() - 1);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private List<Vec3D> pointCloudList;

	
	}
