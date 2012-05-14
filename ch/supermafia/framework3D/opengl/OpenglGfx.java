
package ch.supermafia.framework3D.opengl;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import com.jogamp.common.nio.Buffers;

import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.framework3D.geometry.mesh.PointCloud;

public class OpenglGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void parametricMesh(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		drawGridQuad(parametricMesh3D, gl);
		}
	
	public void parametricMeshPoint(ParametricMesh3D parametricMesh3D, GL2 gl, float pointSize)
		{
		drawGridPoints(parametricMesh3D, gl, pointSize);
		}
	
	public void parametricMeshLines(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		drawGridLines(parametricMesh3D, gl);
		}
	
	public void pointCloudMeshPoints(PointCloud pointCloud, GL2 gl, float pointSize)
		{
		drawPointCloudPoint(pointCloud, gl, pointSize);
		}
	
	public void pointCloudMeshLines(PointCloud pointCloud, GL2 gl)
		{
		drawPointCloudLines(pointCloud, gl);
		}
	
	public void pointCloudMesh(PointCloud pointCloud, GL2 gl)
		{
		drawPointCloudQuads(pointCloud, gl);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawGridQuad(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{
		FloatBuffer points = Buffers.newDirectFloatBuffer(new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2]);
		points.rewind();
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				
				points.put(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x());
				points.put(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y());
				points.put(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			}
		points.rewind();
		
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_QUAD_STRIP, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawGridPoints(ParametricMesh3D parametricMesh3D, GL2 gl, float pointSize)//in main thread but it still has access to table, so synchronized must be set
		{
		gl.glPointSize(pointSize);
		FloatBuffer points = Buffers.newDirectFloatBuffer(new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2]);
		points.rewind();
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				}
			}
		points.rewind();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_POINTS, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawGridPointsVBO(ParametricMesh3D parametricMesh3D, GL2 gl, float pointSize)//in main thread but it still has access to table, so synchronized must be set
		{
		gl.glPointSize(pointSize);
		FloatBuffer points = Buffers.newDirectFloatBuffer(new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2]);
		points.rewind();
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				}
			}
		points.rewind();
		
		//VBO creation
		int nbVBO = 1;
		int[] VBO = new int[nbVBO];
		gl.glGenBuffers(nbVBO, VBO, 0);
		
		//state machine
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		
		// Init VBOs and transfer data.
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBO[0]);
		//transfer data to device
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2, points, GL2.GL_DYNAMIC_DRAW);
		
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBO[0]);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_POINTS, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);
		
		gl.glDeleteBuffers(nbVBO, VBO, 0);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawGridLines(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{
		FloatBuffer points = Buffers.newDirectFloatBuffer(new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2]);
		points.rewind();
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y());
				points.put(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				}
			}
		points.rewind();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_LINES, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawPointCloudPoint(PointCloud pointCloud, GL2 gl, float pointSize)
		{
		gl.glPointSize(pointSize);
		FloatBuffer points = Buffers.newDirectFloatBuffer(pointCloud.getPointCloudList().size() * 3);
		points.rewind();
		for(int iv = 0; iv < pointCloud.getPointCloudList().size(); iv++)
			{
			points.put(pointCloud.getPointCloudList().get(iv).x());
			points.put(pointCloud.getPointCloudList().get(iv).y());
			points.put(pointCloud.getPointCloudList().get(iv).z());
			}
		points.rewind();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_POINTS, 0, pointCloud.getPointCloudList().size() * 3);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawPointCloudLines(PointCloud pointCloud, GL2 gl)
		{
		FloatBuffer points = Buffers.newDirectFloatBuffer(pointCloud.getPointCloudList().size() * 3);
		points.rewind();
		for(int iv = 0; iv < pointCloud.getPointCloudList().size(); iv++)
			{
			points.put(pointCloud.getPointCloudList().get(iv).x());
			points.put(pointCloud.getPointCloudList().get(iv).y());
			points.put(pointCloud.getPointCloudList().get(iv).z());
			}
		points.rewind();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_LINES, 0, pointCloud.getPointCloudList().size() * 3);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private void drawPointCloudQuads(PointCloud pointCloud, GL2 gl)
		{
		FloatBuffer points = Buffers.newDirectFloatBuffer(pointCloud.getPointCloudList().size() * 3);
		points.rewind();
		for(int iv = 0; iv < pointCloud.getPointCloudList().size(); iv++)
			{
			points.put(pointCloud.getPointCloudList().get(iv).x());
			points.put(pointCloud.getPointCloudList().get(iv).y());
			points.put(pointCloud.getPointCloudList().get(iv).z());
			}
		points.rewind();
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);
		gl.glDrawArrays(GL2.GL_QUAD_STRIP, 0, pointCloud.getPointCloudList().size() * 3);
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		}
	
	private int index(int x, int y, int uCount)
		{
		return uCount * y + x;
		}
	}
