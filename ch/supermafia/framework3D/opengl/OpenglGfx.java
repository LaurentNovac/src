
package ch.supermafia.framework3D.opengl;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import com.jogamp.common.nio.Buffers;

import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;

public class OpenglGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public OpenglGfx()
		{
		VBOId = new int[1];
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void createParametricMeshVBO(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		points = Buffers.newDirectFloatBuffer(new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2]);
		
		//VBO creation
		gl.glGenBuffers(1, VBOId, 0);
		
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBOId[0]); //we bind the VBO as we want to modify it
		
		gl.glBufferData(GL2.GL_ARRAY_BUFFER, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2, points, GL2.GL_STREAM_DRAW);//memory allocation, GL_STREAM_DRAW is good when we update the datas at each frame
		
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);//unbind buffer
		}
	
	public void deleteParametricMeshVBO(GL2 gl)
		{
		gl.glDeleteBuffers(1, VBOId, 0);
		}
	
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
		
		renderAsVertexArray(parametricMesh3D, gl, points);
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
		renderAsVertexArray(parametricMesh3D, gl, points);
		}
	
	/*------------------------------------------------------------------*\
	|*							VBO	TODO								*|
	\*------------------------------------------------------------------*/
	
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
		renderAsVertexArray(parametricMesh3D, gl, points);
		}
	
	private void drawGridPointsVBO(ParametricMesh3D parametricMesh3D, GL2 gl, float pointSize)//in main thread but it still has access to table, so synchronized must be set
		{
		gl.glPointSize(pointSize);
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
		
		uploadDataToDevice(parametricMesh3D, gl);
		points.rewind();
		
		renderAsVBO(parametricMesh3D, gl, points);
		
		}
	
	private void uploadDataToDevice(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBOId[0]);
		gl.glBufferSubData(GL2.GL_ARRAY_BUFFER, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2, points);//transfer data to device
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		}
	
	private void renderAsVBO(ParametricMesh3D parametricMesh3D, GL2 gl, FloatBuffer points)
		{
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, VBOId[0]);
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);//activation
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, VBOId[0]);//data specification
		gl.glDrawArrays(GL2.GL_POINTS, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);//rendering
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);//deactivation
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
		}
	
	private void renderAsVertexArray(ParametricMesh3D parametricMesh3D, GL2 gl, FloatBuffer points)
		{
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);//activation
		gl.glVertexPointer(3, GL2.GL_FLOAT, 0, points);//data specification
		gl.glDrawArrays(GL2.GL_POINTS, 0, parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 2);//rendering
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);//deactivation
		}
	
	private int index(int x, int y, int uCount)
		{
		return uCount * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private int[] VBOId;
	private FloatBuffer points;
	}
