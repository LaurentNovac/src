
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import java.util.ArrayList;
import java.util.List;

import ch.supermafia.framework3D.geometry.mesh.TriangleMesh;
import ch.supermafia.framework3D.geometry.mesh.polygonizer.GridCell;
import ch.supermafia.framework3D.geometry.mesh.polygonizer.Polygonizer;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import processing.core.PApplet;
import processing.opengl.*;
import unlekker.modelbuilder.UNav3D;

@SuppressWarnings("serial")
public class GridCellSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, OPENGL);
		buildRect3DGrid();
		polygonize();
		stroke(0);
		strokeWeight(3.0f);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		}
	
	public void draw()
		{
		background(255);
		nav.doTransforms();
		//translate(width / 2, height / 2, 0);
		beginShape(POINTS);
		for(GridCell cell:gridCellList)
			{
			Vec3D[] t = cell.getP();
			for(int i = 0; i < t.length; i++)
				{
				vertex(t[i].x(), t[i].y(), t[i].z());
				}
			}
		endShape();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void buildRect3DGrid()
		{
		gridCellList = new ArrayList<GridCell>();
		for(int i = 0; i < 10; i++)
			{
			for(int j = 0; j < 10; j++)
				{
				for(int k = 0; k < 10; k++)
					{
					GridCell g = new GridCell(new Vec3D(j * 100, i * 100, k * 100), 100);
					gridCellList.add(g);
					}
				}
			
			}
		}
	
	private void polygonize()
		{
		Polygonizer p = new Polygonizer(gridCellList, 5, null);
		triangleMesh = p.compute();
		System.out.println(triangleMesh);
		System.out.println(triangleMesh.getTriangles().size());
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private List<GridCell> gridCellList;
	private UNav3D nav;
	private TriangleMesh triangleMesh;
	}
