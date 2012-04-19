
package ch.supermafia.processing.framework3D.Test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMatrixT
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
	public void testRotate()
		{
		// TODO
		assertTrue(false);
		}
	
	@Test
	public void testTranslate()
		{
		// TODO
		assertTrue(false);
		}
	
	@Test
	public void testScale()
		{
		// TODO
		assertTrue(false);
		}
	
	@Test
	public void testIdentity()
		{
		// TODO
		assertTrue(false);
		}
	
	@Test
	public void testMultRight()
		{
		// TODO
		float[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Matrix4X4Test m1 = new Matrix4X4Test(data);
		float[] data2 = { 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Matrix4X4Test m2 = new Matrix4X4Test(data2);
		float[] dataRes = { 80, 70, 60, 50, 240, 214, 188, 162, 400, 358, 316, 274, 560, 502, 444, 386 };
		m1.multRight(m2);
		System.out.println(m1);
		Matrix4X4Test theoreticalResult = new Matrix4X4Test(dataRes);
		assertTrue(theoreticalResult.isEqual(m1,1E-5f));
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
