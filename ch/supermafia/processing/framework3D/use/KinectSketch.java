
package ch.supermafia.processing.framework3D.use;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.processing.framework3D.mathematics.Function.KinectFunc;
import ch.supermafia.processing.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class KinectSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		kinect = new KinectFunc(this);
		gfx = new ProcessingGfx(this);
		try
			{
			kinectMesh = new ParametricMesh3D(50, 50, kinect);
			kinectMesh.computeTable();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	public void draw()
		{
		background(0);
		lights();
		kinectMesh.computeTable();
		noFill();
		stroke(255);
		gfx.parametricMesh(kinectMesh);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3D kinectMesh;
	private KinectFunc kinect;
	private ProcessingGfx gfx;
	}
