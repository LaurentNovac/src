
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.geometry.mesh.CubicZone;
import ch.supermafia.framework3D.geometry.mesh.PointCloud;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;

public class ZoneIntersectionSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		gfx = new ProcessingGfx(this);
		cubicZone = new CubicZone(new Vec3D(100, 100), 100);
		v = cubicZone.getVertices();
		noStroke();
		nav = new UNav3D(this);
		pointCloud = new PointCloud();
		fillPCL();
		mousePos = new Vec3D();
		}
	
	public void draw()
		{
		background(255);
		mousePos.setX(mouseX);
		mousePos.setY(mouseY);
		cubicZone = new CubicZone(mousePos, 100);
		noStroke();
		nav.doTransforms();
		stroke(0, 255, 0);
		noFill();
		gfx.zone(cubicZone);
		noStroke();
		for(Vec3D v:pointCloud)
			{
			if (cubicZone.intersects(v))
				{
				fill(255, 0, 0);
				}
			else
				{
				fill(0, 0, 255);
				}
			pushMatrix();
			translate(v.x(), v.y(), v.z());
			sphere(10);
			popMatrix();
			}
		}
	
	public void keyPressed()
		{
		if (key == 'r') fillPCL();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void fillPCL()
		{
		pointCloud.clear();
		int n = 20;
		for(int i = 1; i <= n; i++)
			{
			pointCloud.addPoint(new Vec3D(random(width), random(height), random(100)));
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private CubicZone cubicZone;
	private Vec3D[] v;
	private ProcessingGfx gfx;
	private UNav3D nav;
	private PointCloud pointCloud;
	private Vec3D mousePos;
	}
