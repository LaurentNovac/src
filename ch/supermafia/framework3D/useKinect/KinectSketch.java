
package ch.supermafia.framework3D.useKinect;

import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3DUnlekker;
import ch.supermafia.framework3D.mathematics.Function.KinectFunc;
import ch.supermafia.framework3D.processing.ProcessingGfx;
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
		textMode(SCREEN);
		kinect = new KinectFunc(this);
		gfx = new ProcessingGfx(this);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		try
			{
			kinectMesh = new ParametricMesh3DUnlekker(80, 60, kinect, this);
			kinectMesh.computeTable();
			kinectMesh.applyIdentity();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	public void draw()
		{
		background(0);
		pushMatrix();
		//translate(width / 2, height / 2, 0);
		kinect.compute();
		kinectMesh.computeTable();
		nav.doTransforms();
		lights();
		
		noFill();
		stroke(255);
		gfx.parametricMeshPoints(kinectMesh);
		popMatrix();
		fill(255);
		text("" + frameRate, width - 50, height - 50);
		}
	
	public void stop()
		{
		kinect.stop();
		}
	
	public void keyPressed()
		{
		if (key == 'p') kinectMesh.toSTL("kinect.stl");
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3DUnlekker kinectMesh;
	private KinectFunc kinect;
	private ProcessingGfx gfx;
	private UNav3D nav;
	}
