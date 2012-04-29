
package ch.supermafia.processing.framework3D.geometry.mesh;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.supermafia.processing.framework3D.mathematics.Function.FunctionR2R3_I;
import processing.core.PApplet;
import unlekker.modelbuilder.UGeometry;
import unlekker.modelbuilder.UVec3;
import unlekker.modelbuilder.UVertexList;

public class ParametricMesh3DUnlekker extends ParametricMesh3D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ParametricMesh3DUnlekker(int xCount, int yCount, FunctionR2R3_I func, PApplet context) throws InterruptedException
		{
		super(xCount, yCount, func);
		//uVertexList = new UVertexList();
		this.context = context;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void toSTL(final String filename)
		{
		Thread t = new Thread(new Runnable()
			{
				
				@Override
				public void run()
					{
					Logger.getLogger("Unlekker").log(Level.INFO, "adding vertex to list");
					addTableToUVertexList();
					Logger.getLogger("Unlekker").log(Level.INFO, "Done adding vertex to list");
					geom.writeSTL(context, filename);
					}
			});
		t.start();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void addTableToUVertexList()
		{
		UVertexList uVertexList = new UVertexList();
		geom = new UGeometry();
		for(int iv = 0; iv < vCount; iv++)
			{
			for(int iu = 0; iu < uCount; iu++)
				{
				uVertexList.add(new UVec3(table[index(iu, iv)].x(), table[index(iu, iv)].y(), table[index(iu, iv)].z()));
				uVertexList.add(new UVec3(table[index(iu, iv + 1)].x(), table[index(iu, iv + 1)].y(), table[index(iu, iv + 1)].z()));
				}
			}
		geom.quadStrip(uVertexList);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private UGeometry geom;
	private PApplet context;
	}
