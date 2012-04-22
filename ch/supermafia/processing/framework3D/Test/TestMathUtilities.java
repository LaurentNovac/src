
package ch.supermafia.processing.framework3D.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static ch.supermafia.processing.framework3D.mathematics.MathUtilities.map;
import static ch.supermafia.processing.framework3D.mathematics.MathUtilities.isEqual;

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
		x1=12.0f;
		x2=12.1f;
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
		//TODO
		assertTrue(false);
		}
	
	@Test
	public void testCosineInterpolate()
		{
		//TODO
		assertTrue(false);
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
