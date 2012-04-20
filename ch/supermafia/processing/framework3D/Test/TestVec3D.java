
package ch.supermafia.processing.framework3D.Test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;
import ch.supermafia.processing.framework3D.mathematics.MathUtilities;
import static ch.supermafia.processing.framework3D.mathematics.MathUtilities.isEqual;

;

public class TestVec3D
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
	public void testNorm()
		{
		Vec3D v = new Vec3D(1.0f, 0.0f, 0.0f);
		float theoreticalResult = 1.0f;
		float experimentalResult = v.norm();
		assertTrue(theoreticalResult == experimentalResult);
		}
	
	@Test
	public void testNormalize()
		{
		Vec3D v = new Vec3D(10, 32, 24.5f);
		float theoreticalResult = 1.0f;
		v.normalize();
		float experimentalResult = v.norm();
		assertTrue(isEqual(experimentalResult, theoreticalResult, 1E-5f));
		Vec3D v2 = new Vec3D(v).scale(10);
		v2.normalize();
		}
	
	@Test
	public void testNormalize2()
		{
		Vec3D v = new Vec3D(10, 32, 24.5f);
		float theoreticalResult = v.norm();
		v.normalize2();
		float experimentalResult = v.norm();
		assertTrue(isEqual(experimentalResult, theoreticalResult, 0));
		Vec3D unitVec = v.normalize2();
		experimentalResult = unitVec.norm();
		theoreticalResult = 1.0f;
		assertTrue(MathUtilities.isEqual(experimentalResult, experimentalResult, 1E-5f));
		}
	
	@Test
	public void testIdentity()
		{
		Vec3D vec = new Vec3D(1.0f, 3.5f, 3.0f);
		vec.applyIdentity();
		Vec3D theoreticalRes = new Vec3D(1.0f, 3.5f, 3.0f);
		assertTrue(vec.isEqual(theoreticalRes, 0));
		}
	
	@Test
	public void testScale()
		{
		Vec3D v = new Vec3D(1.0f, 1.0f, 1.0f);
		Vec3D theoreticalRes = new Vec3D(3.0f, 2.0f, 4.0f);
		Vec3D experimentalRes = v.scale(3.0f, 2.0f, 4.0f);
		
		assertTrue(isEqual(theoreticalRes.x(), experimentalRes.x(), 1E-5f));
		assertTrue(isEqual(theoreticalRes.y(), experimentalRes.y(), 1E-5f));
		assertTrue(isEqual(theoreticalRes.z(), experimentalRes.z(), 1E-5f));
		}
	
	@Test
	public void testRotate()
		{
		//TODO
		assertTrue(false);
		}
	
	@Test
	public void testIsEqual()
		{
		Vec3D v = new Vec3D(1.0f, 2.0f, 3.0f);
		Vec3D v2 = new Vec3D(1.0f, 2.00001f, 3.00003f);
		
		assertTrue(v.isEqual(v2, 1E-2f));
		
		}
	
	@Test
	public void testTranslate()
		{
		Vec3D v1 = new Vec3D(1.0f, 1.0f, 1.003f);
		Vec3D v2 = new Vec3D(2.0f, 3.0f, 4.002f);
		v1.translate(v2);
		Vec3D theoreticalRes = new Vec3D(3.0f, 4.0f, 5.005f);
		assertTrue(v1.isEqual(theoreticalRes, 1E-15f));
		}
	
	@Test
	public void testSub()
		{
		Vec3D v1 = new Vec3D(1.0f, 1.0f, 1.0f);
		v1.sub(v1);
		Vec3D theoreticalRes = new Vec3D(0.0f, 0.0f, 0.0f);
		assertTrue(v1.isEqual(theoreticalRes, 1E-15f));
		}
	
	@Test
	public void testProjectOn()
		{
		Vec3D e1 = new Vec3D(1, 0, 0);
		Vec3D e2 = new Vec3D(0, 1, 0);
		
		Vec3D experimentalE3 = new Vec3D(0, 0, 0);
		Vec3D theoreticalE3 = e1.projectOn(e2);
		assertTrue(experimentalE3.isEqual(theoreticalE3, 0));
		theoreticalE3 = e2.projectOn(e1);
		assertTrue(experimentalE3.isEqual(theoreticalE3, 0));
		}
	
	@Test
	public void testDot()
		{
		Vec3D v1 = new Vec3D(1.0f, 1.0f, 1.0f);
		
		float experimentalRes = v1.dot(v1);
		float theoreticalRes = 3.0f;
		
		assertTrue(experimentalRes == theoreticalRes);
		
		v1 = new Vec3D(1.0f, 0.0f, 0.0f);
		Vec3D v2 = new Vec3D(0.0f, 1.0f, 0.0f);
		experimentalRes = v1.dot(v2);
		theoreticalRes = 0.0f;
		assertTrue(experimentalRes == theoreticalRes);
		}
	
	@Test
	public void testCross()
		{
		Vec3D e1 = new Vec3D(1, 0, 0);
		Vec3D e2 = new Vec3D(0, 1, 0);
		Vec3D experimentalE3 = e1.cross(e2);
		Vec3D theoreticalE3 = new Vec3D(0, 0, 1);
		assertTrue(experimentalE3.isEqual(theoreticalE3, 0));
		}
	
	@Test
	public void testAngleWith()
		{
		Vec3D v1 = new Vec3D(1.0f, 0.0f, 0.0f);
		Vec3D v2 = new Vec3D(0.0f, 1.0f, 0.0f);
		float experimentalRes = (float)(v1.angleWith(v2) * 180 / Math.PI);
		float theoreticalRes = 90.0f;
		assertTrue(experimentalRes == theoreticalRes);
		}
	
	@Test
	public void testMult()
		{
		Vec3D v1 = new Vec3D(1.0f, 2.0f, 3.0f);
		Vec3D v2 = new Vec3D(1.0f, 2.0f, 3.0f);
		Vec3D theoreticalRes = new Vec3D(1.0f, 4.0f, 9.0f);
		v1.mult(v2);
		assertTrue(v1.isEqual(theoreticalRes, 1E-15f));
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
