
package ch.supermafia.processing.framework3D.geometry.mesh.runnable;

import ch.supermafia.processing.framework3D.geometry.Vec3D;
import processing.core.PApplet;
import processing.core.PVector;
import static ch.supermafia.processing.framework3D.mathematics.MathUtilities.map;

public class MeshDistortionRunnable implements Runnable
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public MeshDistortionRunnable(int tid, int nbThread, Vec3D[] table, int uCount, int vCount, float distortionFactor)
		{
		this.tid = tid;
		this.nbThread = nbThread;
		this.table = table;
		this.uCount = uCount;
		this.vCount = vCount;
		this.distortionFactor = distortionFactor;
		pApplet = new PApplet();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void run()
		{
		computeTable();
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void computeTable()//TODO stop using pApple random func
		{
		while(tid < (uCount + 1 * vCount + 1))
			{
			table[tid].setX(distortionFactor * pApplet.random(-1.0f, 1.0f));
			table[tid].setY(distortionFactor * pApplet.random(-1.0f, 1.0f));
			table[tid].setZ(distortionFactor * pApplet.random(-1.0f, 1.0f));
			
			tid += nbThread;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private int tid;
	private int nbThread;
	private Vec3D[] table;
	private int uCount;
	private int vCount;
	private float distortionFactor;
	private PApplet pApplet;
	}
