
package ch.supermafia.processing.framework3D.opengl;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import com.jogamp.common.nio.Buffers;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;

public class OpenglGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void parametricMesh(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		drawGridVertexArray(parametricMesh3D, gl);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawGridVBO(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{
		float[] vertices = new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2];
		FloatBuffer points = Buffers.newDirectFloatBuffer(vertices);
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
	
	@SuppressWarnings("unused")
	private void drawGridVertexArray(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{
		float[] vertices = new float[parametricMesh3D.getuCount() * parametricMesh3D.getvCount() * 3 * 2];
		FloatBuffer points = Buffers.newDirectFloatBuffer(vertices);
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
	
	@SuppressWarnings("unused")
	private void drawGridImmediateMode(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{ //immediate mode
		for(int iv = 0; iv < parametricMesh3D.getvCount() - 1; iv++)
			{
			gl.glBegin(GL2.GL_QUAD_STRIP);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				gl.glVertex3f(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				gl.glVertex3f(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			gl.glEnd();
			}
		
		}
	
	private int index(int x, int y, int uCount)
		{
		return uCount * y + x;
		}
	}
