
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import codeanticode.syphon.SyphonServer;
import ch.supermafia.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.framework3D.mathematics.Function.Rippling;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;
import processing.core.*;
import processing.opengl.*;

@SuppressWarnings("serial")
public class RipplingEffect3D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, OPENGL);
		smooth();
		uNav3D = new UNav3D(this);
		gfx = new ProcessingGfx(this);
		rippling = new Rippling(0, 600);
		try
			{
			parametricMesh3D = new ParametricMesh3D(80, 80, rippling);
			parametricMesh3D.computeTable();
			parametricMesh3D.applyIdentity();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		canvas = createGraphics(width, height, OPENGL);
		syphonServer = new SyphonServer(this, "Rippling");
		}
	
	public void draw()
		{
		canvas.beginDraw();
		background(0);
		lights();
		pushMatrix();
		int t = millis() / 10;
		rippling.setT(t);
		parametricMesh3D.computeTable();
		uNav3D.doTransforms();
		
		noFill();
		stroke(255);
		strokeWeight(2.0f);
		gfx.meshPoints(parametricMesh3D);
		popMatrix();
		canvas.endDraw();
		syphonServer.sendImage(canvas);
		image(canvas, 0, 0);
		fill(255);
		text("" + frameRate, width - 100, height - 30);
		}
	
	public void stop()
		{
		syphonServer.stop();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ProcessingGfx gfx;
	private Rippling rippling;
	private ParametricMesh3D parametricMesh3D;
	private UNav3D uNav3D;
	private PGraphics canvas;
	private SyphonServer syphonServer;
	}
