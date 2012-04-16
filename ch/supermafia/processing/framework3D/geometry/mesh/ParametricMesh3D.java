
package ch.supermafia.processing.framework3D.geometry.mesh;

import ch.supermafia.processing.framework3D.geometry.Vec3D;
import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshComputeRunnable;
import ch.supermafia.processing.framework3D.geometry.mesh.runnable.MeshDistortionRunnable;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import ch.supermafia.processing.framework3D.mathematics.Function.Cresent;
import ch.supermafia.processing.framework3D.mathematics.Function.Function;
import ch.supermafia.processing.framework3D.mathematics.Function.FunctionR2R3_I;
import ch.supermafia.processing.framework3D.mathematics.Function.KleinCycloid;
import ch.supermafia.processing.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.processing.framework3D.mathematics.Function.Steiner;
import ch.supermafia.processing.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.processing.framework3D.mathematics.Function.Triaxial;
import ch.supermafia.processing.framework3D.mathematics.Function.TwistedFano;
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
		function = Function.TRANGULOID;
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
		return uMin;
		}
	
	public float getuMax()
		{
		return uMax;
		}
	
	public float getvMin()
		{
		return vMin;
		}
	
	public float getvMax()
		{
		return vMax;
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
	
	public void setuMin(float uMin) throws InterruptedException
		{
		this.uMin = uMin;
		computeTable();
		}
	
	public void setuMax(float uMax) throws InterruptedException
		{
		this.uMax = uMin + uMax;
		computeTable();
		}
	
	public void setvMin(float vMin) throws InterruptedException
		{
		this.vMin = vMin;
		computeTable();
		}
	
	public void setvMax(float vMax) throws InterruptedException
		{
		this.vMax = vMin + vMax;
		computeTable();
		}
	
	public void setFunction(Function function)
		{
		this.function = function;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("ParametricMesh [xCount=");
		builder.append(uCount);
		builder.append(", yCount=");
		builder.append(vCount);
		builder.append("]");
		return builder.toString();
		}
	
	@Override
	public void draw()
		{
		drawGrid();
		}
	
	public void distort(float distortionFactor) throws InterruptedException
		{
		int nbThread = Runtime.getRuntime().availableProcessors();
		Thread[] threads = new Thread[nbThread];
		for(int i = 1; i <= nbThread; i++)
			{
			threads[i - 1] = new Thread(new MeshDistortionRunnable(i - 1, nbThread, table, uCount, vCount, distortionFactor));
			threads[i - 1].start();
			}
		for(int i = 1; i <= nbThread; i++)
			{
			threads[i - 1].join();
			}
		}
	
	public void computeTable() throws InterruptedException
		{
		switch(function)
			{
			case TRANGULOID:
				func = new TranguloidTrefoil();
				break;
			case SINSQUARED:
				func = new SinDistSquared();
				break;
			case TWISTEDFANO:
				func = new TwistedFano();
				break;
			case STEINER:
				func = new Steiner();
				break;
			case CRESENT:
				func = new Cresent();
				break;
			case KLEINCYCLOID:
				func = new KleinCycloid(3, 3, 3);
				break;
			case TRIAXIAL:
				func=new Triaxial();
				break;
			default:
				func = new SinDistSquared();
				break;
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
			threads[i - 1].join();
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawGrid()
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
	
	//tools
	protected Vec3D[] table;
	
	protected Function function;
	}
