
package ch.supermafia.processing.toxiclibs.kinect;

import org.openkinect.processing.Kinect;

import processing.core.PApplet;
import processing.core.PVector;

public class UseKinect extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		kinect = new Kinect(this);
		kinect.start();
		kinect.enableDepth(true);
		kinect.processDepthImage(false);
		w = 640;
		h = 480;
		createDepthLookUpTable();
		}
	
	public void draw()
		{
		background(0);
		stroke(255);
		translate(width/2, height/2);
		//rotateY(mouseX * 0.1f);
		int rawDepth[] = kinect.getRawDepth();
		beginShape(POINTS);
		int step = 1;
		for(int x = 0; x < w; x += step)
			{
			for(int y = 0; y < h; y += step)
				{
				int index = x + y * w;
				
				int profondeur = rawDepth[index];//valeur entre 0 et 2047
				PVector v = depthToWorld(x, y, profondeur);//calibration
				
				int facteur = 500;
				v.x = facteur * v.x;
				v.y = facteur * v.y;
				v.z = facteur - v.z * facteur;
				
				pushMatrix();
				translate(v.x, v.y, v.z);
				point(0, 0);
				popMatrix();
				}
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	PVector depthToWorld(int x, int y, int depthValue)
		{
		
		final double fx_d = 1.0 / 5.9421434211923247e+02;
		final double fy_d = 1.0 / 5.9104053696870778e+02;
		final double cx_d = 3.3930780975300314e+02;
		final double cy_d = 2.4273913761751615e+02;
		
		PVector result = new PVector();
		double depth = conversionDepthMeter[depthValue];//rawDepthToMeters(depthValue);
		result.x = (float)((x - cx_d) * depth * fx_d);
		result.y = (float)((y - cy_d) * depth * fy_d);
		result.z = (float)(depth);
		return result;
		}
	
	private void createDepthLookUpTable()
		{
		conversionDepthMeter = new float[2048];
		for(int i = 0; i < 2048; i++)
			{
			conversionDepthMeter[i] = 1.0f / (i * -0.0030711016f + 3.3309495161f);
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float[] conversionDepthMeter;
	private Kinect kinect;
	private int w;
	private int h;
	}
