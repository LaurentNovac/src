
package ch.supermafia.framework3D.mathematics.Function;

import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import ch.supermafia.framework3D.geometry.vector.Vec3D;

/**
 * only works with Processing
 * */
public class FontFunction implements FunctionR2R3_I
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public FontFunction()
		{
		RG.init(context);
		fontShape = RG.getText("Hello World", "FreeSans.ttf", 72, PConstants.CENTER);
		RG.setPolygonizer(RG.UNIFORMLENGTH);
		RG.setPolygonizerLength(2);
		points = fontShape.getPoints();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public Vec3D f(float x, float y)
		{
		return null;
		}
	
	@Override
	public float getUmin()
		{
		return 0;
		}
	
	@Override
	public float getUMax()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	@Override
	public float getVmin()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	@Override
	public float getVMax()
		{
		// TODO Auto-generated method stub
		return 0;
		}
	
	@Override
	public void setLerpParam(float lerpParam)
		{
		// unused
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private RShape fontShape;
	private PApplet context;
	private RPoint[] points;
	}
