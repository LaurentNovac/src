
package ch.supermafia.processing.waves.ripplingEffect;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3DUnlekker;
import ch.supermafia.processing.framework3D.mathematics.Function.Cresent;
import ch.supermafia.processing.framework3D.mathematics.Function.Rippling;
import ch.supermafia.processing.framework3D.mathematics.Function.Triaxial;
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
		size(600, 600, P3D);
		smooth();
		uNav3D = new UNav3D(this);
		uNav3D.setTranslation(width/2,height/2,0);
		gfx = new ProcessingGfx(this);
		rippling = new Rippling(0, width);
		try
			{
			parametricMesh3D = new ParametricMesh3DUnlekker(50, 50, new Cresent(), this);
			parametricMesh3D.computeTable();
			parametricMesh3D.applyIdentity();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}
	
	public void draw()
		{
		background(255);
		System.out.println("Hello");
		lights();
//		int t = millis() / 100;
//		rippling.setT(t);
//		parametricMesh3D.computeTable();
		uNav3D.doTransforms();
		
		fill(255, 0, 0);
		scale(100.0f);
		gfx.parametricMesh(parametricMesh3D);
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ProcessingGfx gfx;
	private Rippling rippling;
	private ParametricMesh3DUnlekker parametricMesh3D;
	private UNav3D uNav3D;
	}
