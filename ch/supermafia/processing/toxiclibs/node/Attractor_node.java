
package ch.supermafia.processing.toxiclibs.node;

import processing.core.PApplet;
import toxi.geom.ReadonlyVec2D;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;
import toxi.processing.ToxiclibsSupport;

public class Attractor_node extends VerletParticle2D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public Attractor_node(Vec2D pos, float radius, NodeLines parent, VerletPhysics2D physicWorld, ToxiclibsSupport gfx)
		{
		super(pos);
		this.radius = radius;
		this.parent = parent;
		this.pos = pos;
		this.physicWorld = physicWorld;
		this.gfx = gfx;
		this.attractionBehavior = new AttractionBehavior2D(this, radius * 10.0f, 1.0f);
		physicWorld.addParticle(this);
		physicWorld.addBehavior(this.attractionBehavior);
		}
	
	public Attractor_node(Attractor_node source)
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
		pos=new Vec2D(parent.getHandX(),parent.getHandY());
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
	private NodeLines parent;
	private Vec2D pos;
	private VerletPhysics2D physicWorld;
	private ToxiclibsSupport gfx;
	private float radius;
	//output
	private AttractionBehavior2D attractionBehavior;
	
	}
