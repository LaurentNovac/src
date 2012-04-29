
package ch.supermafia.processing.framework3D.geometry.mesh;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshComputeRunnable;
import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshDistortionRunnable;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import ch.supermafia.processing.framework3D.mathematics.Function.FunctionR2R3_I;

public class ParametricMesh3D implements Mesh3D_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ParametricMesh3D(int xCount, int yCount, FunctionR2R3_I func) throws InterruptedException
		{
		this.uCount = xCount;
		
		this.vCount = yCount;
		
		this.func = func;
		this.table = new Vec3D[(vCount + 1) * (uCount + 1)];
		lastFunc = func;
		this.distortionFact = 0.0f;
		updateDomain();
		computeTable();
		}
	
	public ParametricMesh3D() throws InterruptedException
		{
		this(3, 2, null);
		}
	
	public ParametricMesh3D(ParametricMesh3D src) throws InterruptedException
		{
		this(src.uCount, src.vCount, src.func);
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public float getuMin()
		{
		return func.getUmin();
		}
	
	public float getuMax()
		{
		return func.getUMax();
		}
	
	public float getvMin()
		{
		return func.getVmin();
		}
	
	public float getvMax()
		{
		return func.getVMax();
		}
	
	public FunctionR2R3_I getFunction()
		{
		return func;
		}
	
	public int getuCount()
		{
		return uCount;
		}
	
	public int getvCount()
		{
		return vCount;
		}
	
	public Vec3D[] getTable()
		{
		return table;
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setuCount(int uCount)
		{
		this.uCount = uCount;
		}
	
	public void setvCount(int vCount)
		{
		this.vCount = vCount;
		}
	
	public void setuMin(float uMin)
		{
		this.uMin = uMin;
		}
	
	public void setuMax(float uMax)
		{
		this.uMax = uMax;
		}
	
	public void setvMin(float vMin)
		{
		this.vMin = vMin;
		}
	
	public void setvMax(float vMax)
		{
		this.vMax = vMax;
		}
	
	public void setFunc(FunctionR2R3_I func)
		{
		this.func = func;
		updateDomain();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Mesh3D_I applyIdentity()
		{
		for(int i = 0; i < vCount * uCount; i++)
			{
			table[i].applyIdentity();
			}
		return this;
		}
	
	@Override
	public Mesh3D_I translate(Vec3D transVec)
		{
		for(int i = 0; i < vCount * uCount; i++)
			{
			table[i].translate(transVec);
			}
		return this;
		}
	
	@Override
	public Mesh3D_I scale(float s)
		{
		for(int i = 0; i < vCount * uCount; i++)
			{
			table[i].scale(s);
			}
		return this;
		}
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("ParametricMesh3D [uCount=");
		builder.append(uCount);
		builder.append(", vCount=");
		builder.append(vCount);
		builder.append(", uMin=");
		builder.append(uMin);
		builder.append(", uMax=");
		builder.append(uMax);
		builder.append(", vMin=");
		builder.append(vMin);
		builder.append(", vMax=");
		builder.append(vMax);
		builder.append(", distortionFact=");
		builder.append(distortionFact);
		builder.append(", function=");
		builder.append("]");
		return builder.toString();
		}
	
	synchronized public void distort(float distortionFactor)
		{
		this.distortionFact = distortionFactor;
		Thread thread = new Thread(new Runnable()
			{
				
				@Override
				public void run()
					{
					int nbThread = Runtime.getRuntime().availableProcessors();
					Thread[] threads = new Thread[nbThread];
					for(int i = 1; i <= nbThread; i++)
						{
						threads[i - 1] = new Thread(new MeshDistortionRunnable(i - 1, nbThread, table, uCount, vCount, ParametricMesh3D.this.distortionFact));
						threads[i - 1].start();
						}
					for(int i = 1; i <= nbThread; i++)
						{
						try
							{
							threads[i - 1].join();
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						}
					}
			});
		thread.start();
		}
	
	synchronized public void computeTable()
		{
		if (func == null)
			{
			Logger.getLogger("mesh computation").log(Level.SEVERE, "A parametric mesh must receive FunctionR2R3_I");
			}
		
		//Logger.getLogger("mesh computation").log(Level.INFO, "Computing mesh");
		
		if (func != lastFunc)
			{
			updateDomain();
			lastFunc = func;
			}
		int nbThread = Runtime.getRuntime().availableProcessors();
		Thread[] threads = new Thread[nbThread];
		for(int i = 1; i <= nbThread; i++)
			{
			threads[i - 1] = new Thread(new MeshComputeRunnable(i - 1, nbThread, table, uCount, vCount, uMin, uMax, vMin, vMax, func));
			threads[i - 1].start();
			}
		for(int i = 1; i <= nbThread; i++)
			{
			try
				{
				threads[i - 1].join();
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}
		//Logger.getLogger("mesh computation").log(Level.INFO, "Done Computing mesh");
		
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	@SuppressWarnings("unused")
	private void computeTableIter()
		{
		for(int iv = 0; iv <= vCount; iv++)
			{
			for(int iu = 0; iu <= uCount; iu++)
				{
				float u = MathUtilities.map(iu, 0, uCount, uMin, uMax);
				float v = MathUtilities.map(iv, 0, vCount, vMin, vMax);
				table[index(iu, iv)] = new Vec3D();
				Vec3D vec = func.f(u, v);
				table[index(iu, iv)] = vec;
				}
			}
		}
	
	protected int index(int x, int y)
		{
		return uCount * y + x;
		}
	
	private void updateDomain()
		{
		this.uMin = func.getUmin();
		this.uMax = func.getUMax();
		this.vMin = func.getVmin();
		this.vMax = func.getVMax();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input/output
	protected int uCount;
	protected int vCount;
	protected float uMin;
	protected float uMax;
	protected float vMin;
	protected float vMax;
	protected FunctionR2R3_I func;
	protected FunctionR2R3_I lastFunc;
	protected float distortionFact;
	//tools
	protected Vec3D[] table;
	
	}
