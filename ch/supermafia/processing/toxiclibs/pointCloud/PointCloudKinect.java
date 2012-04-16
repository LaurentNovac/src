
package ch.supermafia.processing.toxiclibs.pointCloud;

import java.util.ArrayList;
import java.util.List;

import org.openkinect.processing.Kinect;

import processing.core.PApplet;
import processing.core.PVector;

import toxi.geom.PointCloud3D;
import toxi.geom.Vec3D;

public class PointCloudKinect extends PointCloud3D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public PointCloudKinect(PApplet parent)
		{
		this.parent = parent;
		listPoints = new ArrayList<Vec3D>();
		createDepthLookUpTable();
		w = 640;
		h = 480;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void createPointCloud(int[] rawData)
		{
		for(int y = 0; y < h; y++)
			{
			for(int x = 0; x < w; x++)
				{
				Vec3D depth = depthToWorld(x, y, rawData[index(x, y)]);
				addPoint(depth);
				listPoints.add(depth);
				}
			}
		}
	
	public void initKinect()
		{
		kinect = new Kinect(parent);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void createDepthLookUpTable()
		{
		conversionDepthMeter = new float[2048];
		for(int i = 0; i < 2048; i++)
			{
			conversionDepthMeter[i] = 1.0f / (i * -0.0030711016f + 3.3309495161f);
			}
		}
	
	private int index(int x, int y)//row major indexation
		{
		return y * w + x;
		}
	
	private Vec3D depthToWorld(int x, int y, int depthValue)
		{
		
		final double fx_d = 1.0 / 5.9421434211923247e+02;
		final double fy_d = 1.0 / 5.9104053696870778e+02;
		final double cx_d = 3.3930780975300314e+02;
		final double cy_d = 2.4273913761751615e+02;
		
		Vec3D result = new Vec3D();
		double depth = conversionDepthMeter[depthValue];//rawDepthToMeters(depthValue);
		//System.out.println(depth);
		result.x = (float)((x - cx_d) * depth * fx_d);
		result.y = (float)((y - cy_d) * depth * fy_d);
		result.z = (float)(depth);
		return result;
		}
	
	private PVector depthToWorldPVec(int x, int y, int depthValue)
		{
		final double fx_d = 1.0 / 5.9421434211923247e+02;
		final double fy_d = 1.0 / 5.9104053696870778e+02;
		final double cx_d = 3.3930780975300314e+02;
		final double cy_d = 2.4273913761751615e+02;
		
		PVector result = new PVector();
		double depth = conversionDepthMeter[depthValue];//rawDepthToMeters(depthValue);
		//System.out.println(depth);
		result.x = (float)((x - cx_d) * depth * fx_d);
		result.y = (float)((y - cy_d) * depth * fy_d);
		result.z = (float)(depth);
		return result;
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public int getW()
		{
		return w;
		}
	
	public int getH()
		{
		return h;
		}
	
	public List<Vec3D> getListPoints()
		{
		return listPoints;
		}
	
	public Kinect getKinect()
		{
		return kinect;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	//tools
	private float[] conversionDepthMeter;
	private List<Vec3D> listPoints;
	private int w;
	private int h;
	private Kinect kinect;
	private PApplet parent;
	}
