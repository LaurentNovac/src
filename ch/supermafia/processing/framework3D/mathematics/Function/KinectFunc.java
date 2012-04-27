
package ch.supermafia.processing.framework3D.mathematics.Function;

import org.openkinect.processing.Kinect;

import processing.core.PApplet;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

public class KinectFunc implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public KinectFunc(PApplet context)
		{
		this.context = context;
		createDepthLookUpTable();
		initKinect();
		compute();
		}
	
	@Override
	public Vec3D f(float x, float y)
		{
		compute();
		int index = (int)(y * getUMax() + x);
		int depth = rawDepth[index];
		return depthToWorld(x, y, depth);
		}
	
	public void compute()
		{
		rawDepth = kinect.getRawDepth();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	Vec3D depthToWorld(float x, float y, int depthValue)
		{
		
		final double fx_d = 1.0 / 5.9421434211923247e+02;
		final double fy_d = 1.0 / 5.9104053696870778e+02;
		final double cx_d = 3.3930780975300314e+02;
		final double cy_d = 2.4273913761751615e+02;
		
		double depth = conversionDepthMeter[depthValue];//rawDepthToMeters(depthValue);
		float x_ = (float)((x - cx_d) * depth * fx_d);
		float y_ = (float)((y - cy_d) * depth * fy_d);
		float z_ = (float)(depth);
		return new Vec3D(x_, y_, z_);
		}
	
	private void createDepthLookUpTable()
		{
		conversionDepthMeter = new float[2048];
		for(int i = 0; i < 2048; i++)
			{
			conversionDepthMeter[i] = 1.0f / (i * -0.0030711016f + 3.3309495161f);
			}
		}
	
	private void initKinect()
		{
		kinect = new Kinect(context);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false);
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	@Override
	public float getUmin()
		{
		return 0;
		}
	
	@Override
	public float getUMax()
		{
		return 640-1;
		}
	
	@Override
	public float getVmin()
		{
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		return 480-1;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float[] conversionDepthMeter;
	private Kinect kinect;
	private int[] rawDepth;
	private PApplet context;
	
	@Override
	public void setLerpParam(float lerpParam)
		{
		// TODO Auto-generated method stub
		
		}
	
	}
