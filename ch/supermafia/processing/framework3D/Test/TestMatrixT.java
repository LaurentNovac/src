
package ch.supermafia.processing.framework3D.Test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4Identity;
import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4Scale;
import ch.supermafia.processing.framework3D.geometry.matrix.Matrix4x4Translation;
import ch.supermafia.processing.framework3D.geometry.vector.Vec3D;

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
		Vec3D v = new Vec3D(1.0f, 2.0f, 3.0f);
		Matrix4x4Translation mat = new Matrix4x4Translation(v);
		Vec3D vec = new Vec3D(3.0f, 4.0f, 2.0f);
		mat.transformVec(vec);
		Vec3D theoreticalRes = new Vec3D(4.0f, 6.0f, 5.0f);
		assertTrue(vec.isEqual(theoreticalRes, 0));
		}
	
	@Test
	public void testScale()
		{
		Vec3D v = new Vec3D(1.0f, 2.0f, 3.0f);
		Matrix4x4Scale mat = new Matrix4x4Scale(v);
		Vec3D vec = new Vec3D(3.0f, 4.0f, 2.0f);
		mat.transformVec(vec);
		Vec3D theoreticalRes = new Vec3D(3.0f, 8.0f, 6.0f);
		assertTrue(vec.isEqual(theoreticalRes, 0));
		}
	
	@Test
	public void testIdentity()
		{
		float[] data = { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 };
		Matrix4X4Test theoreticalMat = new Matrix4X4Test(data);
		Matrix4x4Identity identity = new Matrix4x4Identity();
		assertTrue(identity.isEqual(theoreticalMat, 0));
		
		Vec3D v=new Vec3D(1.0f, 2.0f, 3.0f);
		identity.transformVec(v);
		Vec3D theoreticalVec=new Vec3D(1.0f, 2.0f, 3.0f);
		assertTrue(v.isEqual(theoreticalVec, 0));
		}
	
	@Test
	public void testMultRight()
		{
		float[] data = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		Matrix4X4Test m1 = new Matrix4X4Test(data);
		float[] data2 = { 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Matrix4X4Test m2 = new Matrix4X4Test(data2);
		float[] dataRes = { 80, 70, 60, 50, 240, 214, 188, 162, 400, 358, 316, 274, 560, 502, 444, 386 };
		m1.multRight(m2);
		Matrix4X4Test theoreticalResult = new Matrix4X4Test(dataRes);
		assertTrue(theoreticalResult.isEqual(m1, 1E-5f));
		}
	
	@Test
	public void testTransformVec()
		{
		Matrix4x4Identity identity = new Matrix4x4Identity();
		Vec3D vec = new Vec3D(12, 23, 33);
		Vec3D theoreticalRes = new Vec3D(12, 23, 33);
		vec = identity.transformVec(vec);
		assertTrue(theoreticalRes.isEqual(vec, 0));
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
