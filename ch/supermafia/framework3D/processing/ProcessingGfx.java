
package ch.supermafia.framework3D.processing;

import ch.supermafia.framework3D.geometry.mesh.CubicZone;
import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.framework3D.geometry.mesh.PointCloud;
import ch.supermafia.framework3D.geometry.mesh.VoxelSpace;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import processing.core.PApplet;

public class ProcessingGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProcessingGfx(PApplet context)
		{
		this.context = context;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void mesh(ParametricMesh3D parametricMesh3D)
		{
		drawGrid(parametricMesh3D);
		}
	
	public void mesh(VoxelSpace voxelSpace, float edgeSize)
		{
		drawVoxelSpace(voxelSpace, edgeSize);
		}
	
	public void meshLines(ParametricMesh3D parametricMesh3D)
		{
		drawGridLines(parametricMesh3D);
		}
	
	public void meshPoints(ParametricMesh3D parametricMesh3D)
		{
		drawGridPoint(parametricMesh3D);
		}
	
	public void meshPoints(PointCloud pointCloud)
		{
		drawPointCloudPoint(pointCloud);
		}
	
	public void meshLines(PointCloud pointCloud)
		{
		drawPointCloudLines(pointCloud);
		}
	
	public void mesh(PointCloud pointCloud)
		{
		drawPointCloudQuads(pointCloud);
		}
	
	public void zone(CubicZone cubicZone)
		{
		Vec3D[] v = cubicZone.getVertices();
		context.beginShape(PApplet.QUAD);
		//rear
		context.vertex(v[0].x(), v[0].y(), v[0].z());
		context.vertex(v[1].x(), v[1].y(), v[1].z());
		context.vertex(v[2].x(), v[2].y(), v[2].z());
		context.vertex(v[3].x(), v[3].y(), v[3].z());
		
		//front
		context.vertex(v[4].x(), v[4].y(), v[4].z());
		context.vertex(v[5].x(), v[5].y(), v[5].z());
		context.vertex(v[6].x(), v[6].y(), v[6].z());
		context.vertex(v[7].x(), v[7].y(), v[7].z());
		
		//top
		context.vertex(v[0].x(), v[0].y(), v[0].z());
		context.vertex(v[4].x(), v[4].y(), v[4].z());
		context.vertex(v[7].x(), v[7].y(), v[7].z());
		context.vertex(v[1].x(), v[1].y(), v[1].z());
		
		//bottom
		context.vertex(v[3].x(), v[3].y(), v[3].z());
		context.vertex(v[2].x(), v[2].y(), v[2].z());
		context.vertex(v[6].x(), v[6].y(), v[6].z());
		context.vertex(v[5].x(), v[5].y(), v[5].z());
		
		//right
		context.vertex(v[0].x(), v[0].y(), v[0].z());
		context.vertex(v[3].x(), v[3].y(), v[3].z());
		context.vertex(v[5].x(), v[5].y(), v[5].z());
		context.vertex(v[4].x(), v[4].y(), v[4].z());
		
		//left
		context.vertex(v[7].x(), v[7].y(), v[7].z());
		context.vertex(v[6].x(), v[6].y(), v[6].z());
		context.vertex(v[2].x(), v[2].y(), v[2].z());
		context.vertex(v[1].x(), v[1].y(), v[1].z());
		
		context.endShape();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void drawGrid(ParametricMesh3D parametricMesh3D)//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.QUAD_STRIP);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				context.vertex(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private void drawGridLines(ParametricMesh3D parametricMesh3D)//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.LINES);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				context.vertex(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private void drawGridPoint(ParametricMesh3D parametricMesh3D)
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.POINTS);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private void drawVoxelSpace(VoxelSpace voxelSpace, float edgeSize)
		{
		Vec3D[][][] vertices = voxelSpace.getVertices();
		context.beginShape(PApplet.POINTS);
		for(int i = 0; i < voxelSpace.getResX(); i++)
			{
			for(int j = 0; j < voxelSpace.getResY(); j++)
				{
				for(int k = 0; k < voxelSpace.getResZ(); k++)
					{
					context.stroke(vertices[i][j][k].x(), vertices[i][j][k].y(), vertices[i][j][k].z());
					context.fill(vertices[i][j][k].x(), vertices[i][j][k].y(), vertices[i][j][k].z());
					context.vertex(i * edgeSize, j * edgeSize, k * edgeSize);
					}
				}
			}
		context.endShape();
		}
	
	private void drawPointCloudPoint(PointCloud pointCloud)
		{
		context.beginShape(PApplet.POINTS);
		for(Vec3D points:pointCloud)
			{
			context.vertex(points.x(), points.y(), points.z());
			}
		context.endShape();
		}
	
	private void drawPointCloudLines(PointCloud pointCloud)
		{
		context.beginShape(PApplet.LINE);
		for(Vec3D points:pointCloud)
			{
			context.vertex(points.x(), points.y(), points.z());
			}
		context.endShape();
		}
	
	private void drawPointCloudQuads(PointCloud pointCloud)
		{
		context.beginShape(PApplet.QUAD_STRIP);
		for(Vec3D points:pointCloud)
			{
			context.vertex(points.x(), points.y(), points.z());
			}
		context.endShape();
		}
	
	private int index(int x, int y, int uCount)
		{
		return uCount * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private PApplet context;
	
	}
