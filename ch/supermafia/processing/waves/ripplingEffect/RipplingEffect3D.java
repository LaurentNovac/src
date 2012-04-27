
package ch.supermafia.processing.waves.ripplingEffect;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;
import ch.supermafia.processing.framework3D.mathematics.Function.Rippling;
import ch.supermafia.processing.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;

@SuppressWarnings("serial")
public class RipplingEffect3D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(1024, 768, P3D);
		smooth();
		uNav3D = new UNav3D(this);
		gfx = new ProcessingGfx(this);
		rippling = new Rippling(0, 600);
		try
			{
			parametricMesh3D = new ParametricMesh3D(80, 80,rippling);
			parametricMesh3D.computeTable();
			parametricMesh3D.applyIdentity();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		textMode(SCREEN);
		}
	
	public void draw()
		{
		background(0);
		lights();
		pushMatrix();
		int t = millis()/10;
		rippling.setT(t);
		parametricMesh3D.computeTable();
		uNav3D.doTransforms();
		
		noFill();
		stroke(255);
		//strokeWeight(2.0f);
		gfx.parametricMeshLines(parametricMesh3D);
		popMatrix();
		fill(255);
		text(""+frameRate, width-100, height-30);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ProcessingGfx gfx;
	private Rippling rippling;
	private ParametricMesh3D parametricMesh3D;
	private UNav3D uNav3D;
	}
