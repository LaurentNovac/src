
package ch.supermafia.processing.toxiclibs.rowMajor;

import processing.core.PApplet;
import toxi.color.TColor;
import toxi.geom.AABB;
import toxi.geom.Vec3D;
import toxi.math.MathUtils;
import toxi.physics3d.VerletParticle3D;
import toxi.physics3d.VerletPhysics3D;
import toxi.physics3d.VerletSpring3D;
import toxi.processing.ToxiclibsSupport;

public class RowMajorTest extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		smooth();
		scaleX = (float)width / (RESX - 1);
		scaleY = (float)width / (RESY - 1);
		gfx = new ToxiclibsSupport(this);
		physics = new VerletPhysics3D();
		springStrength = 0.8f;
		createParticles();
		//lockCornerParticles();
		}
	
	public void draw()
		{
		background(0);
		pushMatrix();
		translate(width / 2, height / 2, 0);
		rotateX(mouseY * 0.1f);
		rotateY(mouseX*0.1f);
		gfx.fill(TColor.WHITE);
		gfx.stroke(TColor.WHITE);
		
		physics.update();
		//drawParticles();
		angle+=0.2;
		rotateZ(radians(-angle));
		drawSprings();
		rotateZ(radians(angle));
		drawSprings();
		//jitter();
		popMatrix();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void createParticles()
		{
		for(int y = 0; y < RESY; y++)//we need to go line by line if we use row major
			{
			for(int x = 0; x < RESX; x++)
				{
				VerletParticle3D p = new VerletParticle3D(new Vec3D(x - RESX / 2, y - RESY / 2, 0).scaleSelf(scaleX, scaleY, 1));
				physics.addParticle(p);//we switch from 2D matrix to row major 1D list
				addConnectionSprings(p, x, y);
				}
			}
		}
	
	private void drawParticles()
		{
		for(VerletParticle3D p:physics.particles)
			{
			gfx.box(new AABB(p, 3));
			}
		}
	
	private void drawSprings()
		{
		for(VerletSpring3D s:physics.springs)
			{
			gfx.line(s.a, s.b);
			}
		}
	
	private void addConnectionSprings(VerletParticle3D p, int x, int y)
		{
		if (x > 0)
			{
			//Row Major indexation
			int previousPIndex = index(x - 1, y);
			VerletParticle3D q = physics.particles.get(previousPIndex);
			VerletSpring3D spring = new VerletSpring3D(p, q, p.distanceTo(q), springStrength);
			physics.addSpring(spring);
			}
		if (y > 0)
			{
			int abovePIndex = index(x, y - 1);
			VerletParticle3D q = physics.particles.get(abovePIndex);
			VerletSpring3D spring = new VerletSpring3D(p, q, p.distanceTo(q), springStrength);
			physics.addSpring(spring);
			}
		}
	
	private void lockCornerParticles()
		{
		physics.particles.get(index(0, 0)).lock();
		physics.particles.get(index(RESX - 1, RESY - 1)).lock();
		physics.particles.get(index(0, RESY - 1)).lock();
		physics.particles.get(index(RESX - 1, 0)).lock();
		}
	
	private void jitter()
		{
		if(MathUtils.randomChance(0.5f))
			{
			physics.particles.get(MathUtils.random(RESX * RESY)).jitter(60);
			}
		}
	
	//Row Major conversion
	private int index(int x, int y)
		{
		return RESX * y + x;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ToxiclibsSupport gfx;
	private VerletPhysics3D physics;
	private final int RESX = 80;
	private final int RESY = 80;
	private float scaleX;
	private float scaleY;
	private float springStrength;
	private float angle;
	}
