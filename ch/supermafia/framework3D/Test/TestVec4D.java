package ch.supermafia.framework3D.Test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.supermafia.framework3D.geometry.vector.Vec3D;
import ch.supermafia.framework3D.geometry.vector.Vec4D;

public class TestVec4D
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
	public void testToCartesian()
		{
		Vec3D vec=new Vec3D(1, 2, 3);
		Vec4D vecH=new Vec4D(vec);
		Vec3D theoreticalRes=new Vec3D(1, 2, 3);
		assertTrue(vecH.toCartesian().isEqual(theoreticalRes, 1E-5f));
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	}
