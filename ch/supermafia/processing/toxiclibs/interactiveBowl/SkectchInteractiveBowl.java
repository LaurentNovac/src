
package ch.supermafia.processing.toxiclibs.interactiveBowl;

import java.util.ArrayList;

import oscP5.OscMessage;
import oscP5.OscP5;

import processing.core.PApplet;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.GravityBehavior2D;
import toxi.processing.ToxiclibsSupport;
import toxi.util.datatypes.BiasedFloatRange;
import toxi.util.datatypes.FloatRange;

public class SkectchInteractiveBowl extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 800, P3D);
		
		handX = width / 2;
		handY = width / 2;
		radius = new BiasedFloatRange(30, 100, 30, 0.6f);
		gfx = new ToxiclibsSupport(this);
		physicWorld = new VerletPhysics2D();
		physicWorld.setDrag(0.05f);
		physicWorld.setWorldBounds(new Rect(0, 0, width, height));
		physicWorld.addBehavior(new GravityBehavior2D(new Vec2D(0.0f, 0.1f)));
		particles = new ArrayList<Particle>();
		attractor = new Attractor_bowl(new Vec2D(mouseX, mouseY), 30, this, physicWorld, gfx);
		int nbr = 200;
		int n = width / nbr;
		for(int i = 1; i <= nbr; i++)
			{
			Particle p = new Particle(this, new Vec2D(i * n, 0), 7, physicWorld, gfx);
			particles.add(p);
			}
		smooth();
		osc = new OscP5(this, PORT);
		}
	
	public void draw()
		{
		fill(0, 0, 0, 128);
		rect(0, 0, width, height);
		
		update();
		if (attractor != null)
			{
			attractor.updateVerlet();
			attractor.displayVerlet();
			}
		stroke(255);
		fill(255);
		for(Particle p:particles)
			{
			p.updateVerlet();
			p.displayVerlet();
			}
		}
	
	public void mousePressed()
		{
		Particle p = new Particle(this, new Vec2D(mouseX, mouseY), radius.pickRandom(), physicWorld, gfx);
		particles.add(p);
		}
	
	public void keyPressed()
		{
		
		}
	
	public void oscEvent(OscMessage message)
		{
		handX = message.get(0).floatValue();
		handY = message.get(1).floatValue();
		}
	
	public void update()
		{
		physicWorld.update();
		}
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public float getHandX()
		{
		return handX;
		}
	
	public float getHandY()
		{
		return handY;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static VerletPhysics2D physicWorld;
	
	private ArrayList<Particle> particles;
	private FloatRange radius;
	private ToxiclibsSupport gfx;
	private Attractor_bowl attractor;
	private static final int PORT = 3000;
	private OscP5 osc;
	private float handX;
	private float handY;
	}
