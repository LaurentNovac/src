
package ch.supermafia.processing.toxiclibs.sketchs;

import processing.core.PApplet;
import toxi.color.TColor;
import toxi.geom.Sphere;
import toxi.geom.mesh.LaplacianSmooth;
import toxi.geom.mesh.WETriangleMesh;
import toxi.processing.ToxiclibsSupport;
import toxi.volume.MeshLatticeBuilder;

public class LatticeMesh extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		gfx = new ToxiclibsSupport(this);
		WETriangleMesh tmp = new WETriangleMesh().addMesh(new Sphere(200).toMesh(SPHERE_RES));

		// the MeshLatticeBuilder constructs a voxel space and
		// traces all edges in the mesh with a volumetric brush
		// it then computes a new mesh of the mesh's isosurface in that voxel space
		mesh = MeshLatticeBuilder.build(tmp, VOXEL_RES, VOXEL_STROKE_WEIGHT);
		for(int i = 0; i < SMOOTH_ITER; i++)
			{
			new LaplacianSmooth().filter(mesh, 1);
			}
		noCursor();
		}
	
	public void draw()
		{
		background(255);
		stroke(0);
		lights();
		translate(width / 2, height / 2, 0);
		rotateX(mouseY * 0.01f);
		rotateY(mouseX * 0.01f);
		// apply wireframe/filled render settings
		
		// draw lattice mesh
		gfx.mesh(mesh);		
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static final int SPHERE_RES = 8;
	private static final int VOXEL_RES = 64;
	private static final int VOXEL_STROKE_WEIGHT = 7;
	private static final int SMOOTH_ITER = 10;
	private static WETriangleMesh mesh;
	private static ToxiclibsSupport gfx;
	}
