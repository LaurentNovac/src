
package ch.supermafia.processing.framework3D.geometry.mesh;

import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshComputeRunnable;
import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshDistortionRunnable;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import ch.supermafia.processing.framework3D.mathematics.Function.Cresent;
import ch.supermafia.processing.framework3D.mathematics.Function.Function_e;
import ch.supermafia.processing.framework3D.mathematics.Function.FunctionR2R3_I;
import ch.supermafia.processing.framework3D.mathematics.Function.HeightMap;
import ch.supermafia.processing.framework3D.mathematics.Function.KinectFunc;
import ch.supermafia.processing.framework3D.mathematics.Function.KleinCycloid;
import ch.supermafia.processing.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.processing.framework3D.mathematics.Function.Steiner;
import ch.supermafia.processing.framework3D.mathematics.Function.TranguloidToTriaxial;
import ch.supermafia.processing.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.processing.framework3D.mathematics.Function.Triaxial;
import processing.core.PApplet;

public class ParametricMesh3D implements Mesh3D_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ParametricMesh3D(int xCount, int yCount, PApplet context) throws InterruptedException
		{
		this.uCount = xCount;
		uMin = (float)(-Math.PI * 2);
		uMax = (float)(Math.PI * 2);
		
		this.vCount = yCount;
		vMin = (float)(-Math.PI * 2);
		vMax = (float)(Math.PI * 2);
		
		this.context = context;
		
		this.table = new Vec3D[(vCount + 1) * (uCount + 1)];
		func = new TranguloidTrefoil();
		lastFunc = func;
		this.distortionFact = 0.0f;
		computeTable();
		}
	
	public ParametricMesh3D(PApplet context) throws InterruptedException
		{
		this(3, 2, context);
		}
	
	public ParametricMesh3D(ParametricMesh3D src) throws InterruptedException
		{
		this(src.uCount, src.vCount, src.context);
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
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void draw()
		{
		drawGrid();
		}
	
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
		//builder.append(function);
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
		Thread thread = new Thread(new Runnable()
			{
				
				public void run()
					{
					System.out.println("computing mesh...");
					
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
					System.out.println("finished computing mesh");
					}
			});
		thread.start();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawGrid()//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < vCount; iv++)
			{
			context.beginShape(PApplet.QUAD_STRIP);
			for(int iu = 0; iu < uCount; iu++)
				{
				context.vertex(table[index(iu, iv)].x(), table[index(iu, iv)].y(), table[index(iu, iv)].z());
				context.vertex(table[index(iu, iv + 1)].x(), table[index(iu, iv + 1)].y(), table[index(iu, iv + 1)].z());
				}
			context.endShape();
			}
		}
	
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
	protected PApplet context;
	protected FunctionR2R3_I func;
	protected FunctionR2R3_I lastFunc;	
	protected float distortionFact;
	//tools
	protected Vec3D[] table;
	
	}
