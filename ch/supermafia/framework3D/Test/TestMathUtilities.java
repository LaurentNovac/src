
package ch.supermafia.framework3D.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.supermafia.framework3D.mathematics.MathUtilities;

import static ch.supermafia.framework3D.mathematics.MathUtilities.isEqual;
import static ch.supermafia.framework3D.mathematics.MathUtilities.map;

public class TestMathUtilities
	{
	
	/*------------------------------------------------------------------*\
	 |*							Constructeurs							*|
	 \*------------------------------------------------------------------*/
	
	@Before
	public void before()
		{
		// rien
		}
	
	@After
	public void after()
		{
		// rien
		}
	
	/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/
	
	@Test
	public void testIsEqual()
		{
		float x1 = 1.40002f;
		float x2 = 1.40003f;
		assertTrue(isEqual(x1, x2, 1E-5f));
		x1 = 12.0f;
		x2 = 12.1f;
		assertFalse(isEqual(x1, x2, 1E-5F));
		}
	
	@Test
	public void testMap()
		{
		float x = 0.5f;
		float minSrc = 0.0f;
		float maxSrc = 1.0f;
		float minDst = 5.0f;
		float maxDst = 10.0f;
		
		float theoreticalResult = 7.5f;
		float experimentalResult = map(x, minSrc, maxSrc, minDst, maxDst);
		assertTrue(theoreticalResult == experimentalResult);
		}
	
	@Test
	public void testLinearInterpolate()
		{
		float x1 = 1.0f;
		float x2 = 2.0f;
		float theoreticalRes = 1.5f;
		float experimentylRes = MathUtilities.linearInterpolate(x1, x2, 0.5f);
		assertTrue(MathUtilities.isEqual(theoreticalRes, experimentylRes, 0));
		}
	
	@Test
	public void testCosineInterpolate()
		{
		//TODO
		assertTrue(false);
		}
	
	@Test
	public void testMin()
		{
		float a = 1.3f;
		float b = 2.1f;
		
		float theoreticalRes = 1.3f;
		float experimentalRes = MathUtilities.min(a, b);
		assertTrue(theoreticalRes == experimentalRes);
		}
	
	@Test
	public void testMax()
		{
		float a = 1.3f;
		float b = 2.1f;
		
		float theoreticalRes = 2.1f;
		float experimentalRes = MathUtilities.max(a, b);
		assertTrue(theoreticalRes == experimentalRes);
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
