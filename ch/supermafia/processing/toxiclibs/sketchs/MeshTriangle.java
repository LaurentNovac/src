
package ch.supermafia.processing.toxiclibs.sketchs;

import processing.core.PApplet;
import toxi.geom.mesh.TriangleMesh;
import toxi.processing.ToxiclibsSupport;

public class MeshTriangle extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800,600);
		gfx=new ToxiclibsSupport(this);
		triMesh=new TriangleMesh();
		translate(width/2, height/2);
		noLoop();
		}
	
	public void draw()
		{
		gfx.mesh(triMesh,true);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static TriangleMesh triMesh;
	private static ToxiclibsSupport gfx;
	}
