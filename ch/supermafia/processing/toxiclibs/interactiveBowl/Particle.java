
package ch.supermafia.processing.toxiclibs.interactiveBowl;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;
import toxi.processing.ToxiclibsSupport;

public class Particle extends VerletParticle2D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Particle(PApplet p, Vec2D pos, float radius, VerletPhysics2D physicWorld, ToxiclibsSupport gfx)
		{
		super(pos);
		this.parent = p;
		this.radius = radius;
		this.physicWorld = physicWorld;
		this.gfx = gfx;
		this.attractionBehavior = new AttractionBehavior2D(this, radius * 2.0f, -1.0f);
		physicWorld.addParticle(this);
		physicWorld.addBehavior(this.attractionBehavior);
		}
	
	public Particle(Particle source)
		{
		this(source.parent, source.pos, source.radius, source.physicWorld, source.gfx);
		}
	
	
	/*------------------------------------------------------------------*\
	|*							MŽthodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		return "Particle [radius=" + radius + ", parent=" + parent + ", pos=" + pos + ", physicWorld=" + physicWorld + ", gfx=" + gfx + ", attractionBehavior=" + attractionBehavior + "]";
		}

	public void updateVerlet()
		{
		pos = this;//VerletPhysics2D inherits Vec2D
		}
	
	public void displayVerlet()
		{
		parent.fill(255);
		gfx.circle(pos, radius * 2);//renders a circle for Processing
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	//input
	private float radius;
	private PApplet parent;
	private Vec2D pos;
	private VerletPhysics2D physicWorld;
	private ToxiclibsSupport gfx;
	//output
	private AttractionBehavior2D attractionBehavior;
	}
