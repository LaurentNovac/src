
package ch.supermafia.processing.framework3D.use;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.processing.framework3D.mathematics.Function.KinectFunc;
import ch.supermafia.processing.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;

@SuppressWarnings("serial")
public class KinectSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		kinect = new KinectFunc(this);
		gfx = new ProcessingGfx(this);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
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
		nav.doTransforms();
		lights();
		
		noFill();
		stroke(255);
		scale(40.0f);
		gfx.parametricMeshPoints(kinectMesh);
		}
	
	public void keyPressed()
		{
		if (key == 'c') kinectMesh.computeTable();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3D kinectMesh;
	private KinectFunc kinect;
	private ProcessingGfx gfx;
	private UNav3D nav;
	}
