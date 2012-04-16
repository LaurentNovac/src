
package ch.supermafia.processing.framework3D.mathematics;

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
	
	}
