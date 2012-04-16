
package ch.supermafia.processing.toxiclibs.interactiveBowl;

import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;
import toxi.processing.ToxiclibsSupport;

public class Attractor_bowl extends VerletParticle2D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Attractor_bowl(Vec2D pos, float radius, SkectchInteractiveBowl parent, VerletPhysics2D physicWorld, ToxiclibsSupport gfx)
		{
		super(pos);
		this.radius = radius;
		this.parent = parent;
		this.pos = pos;
		this.physicWorld = physicWorld;
		this.gfx = gfx;
		this.attractionBehavior = new AttractionBehavior2D(this, radius * 40.0f, 1.0f);
		physicWorld.addParticle(this);
		physicWorld.addBehavior(this.attractionBehavior);
		}
	
	public Attractor_bowl(Attractor_bowl source)
		{
		this(source.pos,source.radius,source.parent,source.physicWorld,source.gfx);
		}
	
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	@Override
	public String toString()
		{
		return "Attractor [parent=" + parent + ", pos=" + pos + ", physicWorld=" + physicWorld + ", gfx=" + gfx + ", radius=" + radius + ", attractionBehavior=" + attractionBehavior + "]";
		}

	public void updateVerlet()
		{
		pos=new Vec2D(parent.mouseX,parent.mouseY);
		this.set(pos);
		}

	public void displayVerlet()
		{
		parent.noStroke();
		parent.fill(27,102,227);
		gfx.circle(pos, 10);
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	//input
	private SkectchInteractiveBowl parent;
	private Vec2D pos;
	private VerletPhysics2D physicWorld;
	private ToxiclibsSupport gfx;
	private float radius;
	//output
	private AttractionBehavior2D attractionBehavior;
	
	}
