
package ch.supermafia.processing.framework3D.geometry.mesh.runnable;

import ch.supermafia.processing.framework3D.geometry.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import ch.supermafia.processing.framework3D.mathematics.Function.FunctionR2R3_I;
import processing.core.PVector;

public class MeshComputeRunnable implements Runnable
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public MeshComputeRunnable(int tid, int nbThread, Vec3D[] table, int uCount, int vCount, float uMin, float uMax, float vMin, float vMax, FunctionR2R3_I func)
		{
		this.tid = tid;
		this.nbThread = nbThread;
		this.table = table;
		this.uCount = uCount-1;
		this.vCount = vCount;
		this.uMin = uMin;
		this.uMax = uMax;
		this.vMin = vMin;
		this.vMax = vMax;
		this.func = func;
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
	private void computeTable()
		{
		
		while(tid <= vCount)
			{
			for(int iu = 0; iu <= uCount; iu++)
				{
				float u = MathUtilities.map(iu, 0, uCount, uMin, uMax);
				float v = MathUtilities.map(tid, 0, vCount, vMin, vMax);
				table[index(iu, tid)] = new Vec3D();
				Vec3D vec = func.f(u, v);
				table[index(iu, tid)].setX(vec.x());
				table[index(iu, tid)].setY(vec.y());
				table[index(iu, tid)].setZ(vec.z());
				}
			tid += nbThread;
			}
		}
	
	private int index(int x, int y)
		{
		return (uCount+1) * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private int tid;
	private int nbThread;
	private Vec3D[] table;
	//private PVector[] table;
	private int uCount;
	private int vCount;
	private float uMin;
	private float uMax;
	private float vMin;
	private float vMax;
	private FunctionR2R3_I func;
	}
