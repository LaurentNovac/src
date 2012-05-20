
package ch.supermafia.framework3D.opengl;

import java.awt.Dimension;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;


import com.jogamp.opengl.util.FPSAnimator;

@SuppressWarnings("serial")
public class JoglTemplate extends JFrame implements GLEventListener
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public JoglTemplate()
		{
		// Get the default OpenGL profile that best reflect your running platform.
		GLProfile glp = GLProfile.getDefault();
		// Specifies a set of OpenGL capabilities, based on your profile.
		GLCapabilities caps = new GLCapabilities(glp);
		// Allocate a GLDrawable, based on your OpenGL capabilities.
		GLJPanel canvas = new GLJPanel(caps);
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		canvas.addGLEventListener(this);
		
		// Create a animator that drives canvas' display() at 60 fps. 
		final FPSAnimator animator = new FPSAnimator(canvas, FPS);
		setContentPane(canvas);
		//add(canvas);
		setTitle("OpenGL template");
		setVisible(true);
		setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		animator.start();
		
		// ----- Your constructor code here -----
		// ......
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void init(GLAutoDrawable drawable)
		{
		// Get the OpenGL graphics context
		gl = drawable.getGL().getGL2();
		// Get GL Utilities after the GL context created.		
		glu = GLU.createGLU();
		// Enable smooth shading, which blends colors nicely, and smoothes out lighting.
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		// Set background color in RGBA. Alpha: 0 (transparent) 1 (opaque) 
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// Setup the depth buffer and enable the depth testing
		gl.glClearDepth(1.0f); // clear z-buffer to the farthest
		gl.glEnable(GL.GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL.GL_LEQUAL); // the type of depth test to do
		// Do the best perspective correction
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		// ----- Your OpenGL initialization code here -----
		// ......
		
		}
	
	@Override
	public void display(GLAutoDrawable drawable)
		{
		// Get the OpenGL graphics context
		gl = drawable.getGL().getGL2();
		// Clear the color and the depth buffers
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		// Reset the view (x, y, z axes back to normal)
		gl.glLoadIdentity();
		
		// ----- Your OpenGL rendering code here -----
		// ......

		}
	
	@Override
	public void dispose(GLAutoDrawable drawable)
		{
		// hardly used
		}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
		{
		// Get the OpenGL graphics context
		gl = drawable.getGL().getGL2();
		
		height = (height == 0) ? 1 : height; // Prevent divide by zero
		float aspect = (float)width / height; // Compute aspect ratio
		// Set view port to cover full screen 
		gl.glViewport(0, 0, width, height);
		
		// Set up the projection matrix - choose perspective view
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity(); // reset
		// Angle of view (fovy) is 45 degrees (in the up y-direction). Based on this
		// canvas's aspect ratio. Clipping z-near is 0.1f and z-far is 100.0f.
		glu.gluPerspective(45.0f, aspect, 0.1f, 100.0f); // fovy, aspect, zNear, zFar
		
		// Switch to the model-view transform
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	protected final int CANVAS_WIDTH = 1280;
	protected final int CANVAS_HEIGHT = 720;
	protected final int FPS = 60;
	protected GLU glu;
	protected GL2 gl;
	}
