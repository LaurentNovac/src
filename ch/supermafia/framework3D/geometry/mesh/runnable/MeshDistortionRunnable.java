
package ch.supermafia.framework3D.geometry.mesh.runnable;

import ch.supermafia.framework3D.geometry.vector.Vec3D;
import processing.core.PApplet;
import processing.core.PVector;
import static ch.supermafia.framework3D.mathematics.MathUtilities.map;

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
			float r=distortionFactor * pApplet.random(-1.0f, 1.0f);
			table[tid].setX(table[tid].getX()+r);
			table[tid].setY(table[tid].getY()+r);
			//table[tid].setZ(table[tid].getZ()+r);
			
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
