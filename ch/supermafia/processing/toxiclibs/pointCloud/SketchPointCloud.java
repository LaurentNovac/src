
package ch.supermafia.processing.toxiclibs.pointCloud;

import java.util.List;

import processing.core.PApplet;
import toxi.color.TColor;
import toxi.geom.Vec3D;
import toxi.geom.mesh.Terrain;
import toxi.geom.mesh.TriangleMesh;
import toxi.processing.ToxiclibsSupport;

@SuppressWarnings("serial")
public class SketchPointCloud extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		gfx = new ToxiclibsSupport(this);
		pointCloudKinect = new PointCloudKinect(this);
		pointCloudKinect.initKinect();
		//noLoop();
		}
	
	public void draw()
		{
		background(0);
		lights();
		int[] rawData = pointCloudKinect.getKinect().getRawDepth();
		pointCloudKinect.createPointCloud(rawData);
		createMesh();
		
		translate(width / 2, height / 2, 0);
		rotateX(mouseY * 0.01f);
		rotateY(mouseX * 0.01f);
		scale(500);
		System.out.println("rendering pcl...");
		gfx.stroke(TColor.WHITE);
		noStroke();
		gfx.fill(TColor.WHITE);
		gfx.mesh(mesh);
		System.out.println("rendered pcl");
		}
	
	public void mousePressed()
		{
		//mesh.saveAsOBJ("pcl.obj");
		redraw();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void createTerrain()
		{
		float[] elevation = new float[pointCloudKinect.getW() * pointCloudKinect.getH()];
		terrain = new Terrain(pointCloudKinect.getW(), pointCloudKinect.getH(), 4);
		int index = 0;
		for(Vec3D p:pointCloudKinect)
			{
			elevation[index++] = p.z() * 100;
			}
		terrain.setElevation(elevation);
		mesh = new TriangleMesh();
		mesh = (TriangleMesh)terrain.toMesh();
		}
	
	private void createMesh()
		{
		List<Vec3D> listPoint = pointCloudKinect.getListPoints();
		mesh = new TriangleMesh();
		for(int i = 0; i < listPoint.size(); i += 3)
			{
			mesh.addFace(listPoint.get(i), listPoint.get(i + 1), listPoint.get(i + 2));
			}
		mesh.computeFaceNormals();
		mesh.computeVertexNormals();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private PointCloudKinect pointCloudKinect;
	private ToxiclibsSupport gfx;
	private Terrain terrain;
	private TriangleMesh mesh;
	
	}
