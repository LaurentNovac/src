
package ch.supermafia.framework3D.use.frameworkDemo.processing;

import ch.supermafia.framework3D.geometry.mesh.PointCloud;
import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.mathematics.Function.Rippling;
import ch.supermafia.framework3D.mathematics.Function.SinDistSquared;
import ch.supermafia.framework3D.mathematics.Function.TranguloidTrefoil;
import ch.supermafia.framework3D.processing.ProcessingGfx;
import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;
import unlekker.modelbuilder.UNav3D;

@SuppressWarnings("serial")
public class PointCloudSketch extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		gfx = new ProcessingGfx(this);
		RG.init(this);
		RG.setPolygonizer(RG.UNIFORMLENGTH);
		RG.setPolygonizerLength(1);
		shapeFont = RG.getText("Camille", "Georgia.ttf", 72, CENTER);
		points = shapeFont.getPoints();
		pointCloud = new PointCloud();
		for(RPoint p:points)
			{
			pointCloud.addPoint(new Vec3D(p.x, p.y));
			}
		rippling = new Rippling(1, width / 3);
		pointCloud.applyFunction(new SinDistSquared());
		}
	
	public void draw()
		{
		background(255);
		int t = millis() / 10;
		rippling.setT(t);
		nav.doTransforms();
		strokeWeight(4.0f);
		gfx.pointCloudMeshPoints(pointCloud);
		//pointCloud.applyFunction(rippling);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private RPoint[] points;
	private RShape shapeFont;
	private PointCloud pointCloud;
	private ProcessingGfx gfx;
	private Rippling rippling;
	private UNav3D nav;
	}
