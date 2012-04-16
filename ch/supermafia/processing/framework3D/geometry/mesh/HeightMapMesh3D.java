
package ch.supermafia.processing.framework3D.geometry.mesh;

import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PVector;

import ch.supermafia.processing.framework3D.geometry.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import ch.supermafia.processing.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.processing.framework3D.mathematics.Function.TranguloidTrefoil;

public class HeightMapMesh3D implements Mesh3D_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public HeightMapMesh3D(BufferedImage bufferedImage, PApplet context, int uCount, int vCount)
		{
		this.bufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		this.bufferedImage.setData(bufferedImage.getRaster());
		this.context = context;
		this.uCount = uCount;
		this.vCount = vCount;
		this.uMin = 0.0f;
		this.uMax = bufferedImage.getWidth();
		this.vMin = 0.0f;
		this.vMax = bufferedImage.getHeight();
		this.table = new PVector[(uCount + 1) * (vCount + 1)];
		computeTable();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void draw() throws InterruptedException
		{
		drawGrid();
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
	private void drawGrid()
		{
		for(int iv = 0; iv < vCount; iv++)
			{
			context.beginShape(PApplet.QUAD_STRIP);
			for(int iu = 0; iu < uCount; iu++)
				{
				context.vertex(table[index(iu, iv)].x, table[index(iu, iv)].y, table[index(iu, iv)].z);
				context.vertex(table[index(iu, iv + 1)].x, table[index(iu, iv + 1)].y, table[index(iu, iv + 1)].z);
				}
			context.endShape();
			}
		}
	
	private void computeTable()//TODO
		{
		for(int iv = 0; iv <= vCount; iv++)
			{
			for(int iu = 0; iu <= uCount; iu++)
				{
				int u = (int)MathUtilities.map(iu, 0, uCount, uMin, uMax);
				int v = (int)MathUtilities.map(iv, 0, vCount, vMin, vMax);
				table[index(iu, iv)] = new PVector();
				
				table[index(iu, iv)].x = u;
				table[index(iu, iv)].y = v;
				table[index(iu, iv)].z = bufferedImage.getRGB(u, v);//TODO check
				}
			}
		}
	
	private int index(int x, int y)
		{
		return uCount * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private BufferedImage bufferedImage;
	private PApplet context;
	private int uCount;
	private int vCount;
	private float uMin;
	private float uMax;
	private float vMin;
	private float vMax;
	private PVector[] table;
	}
