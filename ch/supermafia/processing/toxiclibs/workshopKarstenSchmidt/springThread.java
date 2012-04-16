
package ch.supermafia.processing.toxiclibs.workshopKarstenSchmidt;

import processing.core.PApplet;
import toxi.color.TColor;
import toxi.geom.AABB;
import toxi.physics3d.VerletParticle3D;
import toxi.physics3d.VerletPhysics3D;
import toxi.processing.ToxiclibsSupport;

public class springThread implements Runnable
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public springThread(int indexBegin, int indexEnd, ToxiclibsSupport gfx, PApplet parent, VerletPhysics3D physics)
		{
		this.indexBegin = indexBegin;
		this.indexEnd = indexEnd;
		this.gfx = gfx;
		this.parent = parent;
		this.physics = physics;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public void run()
		{
		System.out.println(indexBegin);
		System.out.println(indexEnd);
		for(int i = indexBegin; i < indexEnd; i++)
			{
			gfx.fill(TColor.newHSV(parent.map(physics.particles.get(i).z, -50, 50, 0, 1f), 1.0f, 1.0f));
			gfx.box(new AABB(physics.particles.get(i), 1));
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//input
	private int indexBegin;
	private int indexEnd;
	private ToxiclibsSupport gfx;
	private PApplet parent;
	private VerletPhysics3D physics;
	}
