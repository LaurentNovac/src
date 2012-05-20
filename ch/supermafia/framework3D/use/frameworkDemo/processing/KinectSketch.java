
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.framework3D.mathematics.Function.KinectFunc;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import processing.opengl.*;
import unlekker.modelbuilder.UNav3D;

public class KinectSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, OPENGL);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		gfx = new ProcessingGfx(this);
		try
			{
			kinectF = new KinectFunc(this);
			kinectMesh = new ParametricMesh3D(200, 200, kinectF);
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
		stroke(255);
		kinectF.compute();
		kinectMesh.computeTable();
		gfx.meshPoints(kinectMesh);
		}
	
	public void stop()
		{
		kinectF.stop();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ParametricMesh3D kinectMesh;
	private ProcessingGfx gfx;
	private KinectFunc kinectF;
	private UNav3D nav;
	}
