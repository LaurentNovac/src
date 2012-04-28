
package ch.supermafia.processing.waves.lissajous.runnable;

import processing.core.PApplet;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import ch.supermafia.processing.waves.lissajous.LissaJousSketch;

public class LissajousRunnable implements Runnable
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public LissajousRunnable(int tid, int nbThread, Vec3D[] lissajousPoints, int pointCount, int factorX, int factorY, float frequX2, float frequY2, float frequCarrier2, int phi)
		{
		this.tid = tid;
		this.nbThread = nbThread;
		this.pointCount = pointCount;
		this.factorX = factorX;
		this.factorY = factorY;
		this.frequX = frequX2;
		this.frequY = frequY2;
		this.frequCarrier = frequCarrier2;
		this.phi = phi;
		this.lissajousPoints = lissajousPoints;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void run()
		{
		while(tid < pointCount)
			{
			float angle = PApplet.map(tid, 0, pointCount, 0, LissaJousSketch.TWO_PI);
			float x = factorX * PApplet.sin(angle * frequX + PApplet.radians(phi)) * PApplet.cos(angle * frequCarrier);
			float y = factorY * PApplet.sin(angle * frequY) * PApplet.cos(angle * frequCarrier);
			Vec3D v = new Vec3D(x, y);
			lissajousPoints[tid] = v;
			tid += nbThread;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private int tid;
	private int nbThread;
	private int pointCount;
	private int factorX;
	private int factorY;
	private float frequX;
	private float frequY;
	private float frequCarrier;
	private int phi;
	private Vec3D[] lissajousPoints;
	}
