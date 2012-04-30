
package ch.supermafia.jogl;

import javax.media.opengl.GLAutoDrawable;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.processing.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.processing.framework3D.opengl.OpenglGfx;

@SuppressWarnings("serial")
public class JoglMesh extends JoglTemplate
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JoglMesh()
		{
		gfx = new OpenglGfx();
		rotY = 0;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void init(GLAutoDrawable drawable)
		{
		super.init(drawable);
		}
	
	@Override
	public void display(GLAutoDrawable drawable)
		{
		super.display(drawable);
		rotY += 1f;
		gl.glTranslatef(-1.5f, 0.0f, -30.0f); // translate left and into the screen
		
		gl.glColor3d(255, 0, 0);
		gl.glRotatef(rotY, 0, 1, 0);
		try
			{
			ParametricMesh3D mesh = new ParametricMesh3D(100, 100, new TranguloidTrefoil());
			mesh.computeTable();
			gfx.parametricMesh(mesh, gl);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private OpenglGfx gfx;
	private float rotY;
	}
