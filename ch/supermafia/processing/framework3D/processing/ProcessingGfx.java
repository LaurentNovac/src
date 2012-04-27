
package ch.supermafia.processing.framework3D.processing;

import ch.supermafia.processing.framework3D.geometry.mesh.ParametricMesh3D;
import processing.core.PApplet;

public class ProcessingGfx
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ProcessingGfx(PApplet context)
		{
		this.context = context;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void parametricMesh(ParametricMesh3D parametricMesh3D)
		{
		drawGrid(parametricMesh3D);
		}

	public void parametricMeshLines(ParametricMesh3D parametricMesh3D)
		{
		drawGridLines(parametricMesh3D);
		}
	
	public void parametricMeshPoints(ParametricMesh3D parametricMesh3D)
		{
		drawGridPoint(parametricMesh3D);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void drawGrid(ParametricMesh3D parametricMesh3D)//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.QUAD_STRIP);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				context.vertex(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private void drawGridLines(ParametricMesh3D parametricMesh3D)//in main thread but it still has access to table, so synchronized must be set
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.LINES);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				context.vertex(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private void drawGridPoint(ParametricMesh3D parametricMesh3D)
		{
		for(int iv = 0; iv < parametricMesh3D.getvCount(); iv++)
			{
			context.beginShape(PApplet.POINTS);
			for(int iu = 0; iu < parametricMesh3D.getuCount(); iu++)
				{
				context.vertex(parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv, parametricMesh3D.getuCount())].z());
				context.vertex(parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].x(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].y(), parametricMesh3D.getTable()[index(iu, iv + 1, parametricMesh3D.getuCount())].z());
				}
			context.endShape();
			}
		}
	
	private int index(int x, int y, int uCount)
		{
		return uCount * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private PApplet context;
	
	}
