
package ch.supermafia.processing.toxiclibs.sketchs;

import processing.core.PApplet;
import toxi.geom.*;
import toxi.geom.mesh.*;
import toxi.math.noise.*;
import toxi.processing.*;

public class BezierMesh extends PApplet
	{
	public static ToxiclibsSupport gfx;
	public static Mesh3D mesh;
	public static BezierPatch patch;
	public static float NS = 0.05f;
	public static float SIZE = 100;
	public static float AMP = SIZE * 4;
	
	public void setup()
		{
		gfx = new ToxiclibsSupport(this);
		size(800, 600, P3D);//needs 3d rendering
		noFill();
		strokeWeight(1);
		stroke(255);
		}
	
	public void draw()
		{
		updateMesh();
		background(0);
		translate(width / 2, height / 2, 0);
		gfx.mesh(mesh, true);
		}
	public void updateMesh()
		{
		  double phase=frameCount*NS*0.1;

		  patch=new BezierPatch();
		  for (int y=0;y<4;y++)
		  {
		    for (int x=0;x<4;x++)
		    {
		      float xx=x*SIZE*2;//xx,yy,zz are control points of the bezier surface
		      float yy=y*SIZE;
		      float zz = (float) (SimplexNoise.noise(xx * NS, yy * NS,phase) * AMP);
		      patch.set(x, y, new Vec3D(xx, yy, zz));
		      mesh=patch.toMesh(32);
		      mesh.center(null);
		    }
		  }
		}
	}
