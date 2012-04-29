
package ch.supermafia.jogl;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import ch.supermafia.processing.framework3D.opengl.OpenglGfx;

@SuppressWarnings("serial")
public class JoglMesh extends JoglTemplate
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JoglMesh()
		{
		gfx=new OpenglGfx();
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
		//gl.glTranslated(this.getWidth()/2, this.getHeight()/2, 0);
//		gl.glColor3d(255, 0, 0);
//		try
//			{
//			ParametricMesh3D mesh = new ParametricMesh3D(50, 50, new SinDistSquared());
//			gfx.parametricMesh(mesh, gl);
//			}
//		catch (InterruptedException e)
//			{
//			e.printStackTrace();
//			}
		// ----- Render a triangle -----
		gl.glTranslatef(-1.5f, 0.0f, -6.0f); // translate left and into the screen
		gl.glBegin(GL2.GL_TRIANGLES); // draw using triangles
		// Define groups of 3 vertices in counter-clockwise (CCW) order
		gl.glVertex3f(0.0f, 1.0f, 0.0f);
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glEnd();
		   
		// ----- Render a quad -----
		// translate right, relative to the previous translation
		gl.glTranslatef(3.0f, 0.0f, 0.0f);
		gl.glBegin(GL2.GL_QUADS); // draw using quads
		// Define groups of 4 vertices in CCW order
		gl.glVertex3f(-1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, 1.0f, 0.0f);
		gl.glVertex3f(1.0f, -1.0f, 0.0f);
		gl.glVertex3f(-1.0f, -1.0f, 0.0f);
		gl.glEnd();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private OpenglGfx gfx;
	
	}
