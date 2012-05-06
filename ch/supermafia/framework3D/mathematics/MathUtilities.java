
package ch.supermafia.framework3D.mathematics;

public final class MathUtilities
	{
	
	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/
	
	public static float map(float value, float min1, float max1, float min2, float max2)
		{
		float slope = (max2 - min2) / (max1 - min1);
		return min2 + value * slope;
		}
	
	public static boolean isEqual(float u, float v, float epsilon)
		{
		if (u == 0 || v == 0)
			{
			
			return Math.abs(v - u) <= epsilon;
			}
		else
			{
			return Math.abs((u - v) / v) <= epsilon;
			}
		
		}
	
	public static float linearInterpolate(float x1, float x2, float mu)
		{
		return x1 * (1 - mu) + x2 * mu;
		}
	
	public static float cosineInterpolate(float y1, float y2, float mu)
		{
		float mu2;
		
		mu2 = (float)((1 - Math.cos(mu * Math.PI)) / 2);
		return (y1 * (1 - mu2) + y2 * mu2);
		}
	
	public static float min(float a, float b)
		{
		return (a <= b ? a : b);
		}
	
	public static float max(float a, float b)
		{
		return (a > b ? a : b);
		}
	}
