
package ch.supermafia.processing.framework3D.opengl;

import javax.media.opengl.GL2;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;

public class OpenglGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void parametricMesh(ParametricMesh3D parametricMesh3D, GL2 gl)
		{
		drawGrid(parametricMesh3D, gl);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawGrid(ParametricMesh3D parametricMesh3D, GL2 gl)//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
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
