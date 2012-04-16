
package ch.supermafia.processing.toxiclibs.node;

import java.util.ArrayList;

import oscP5.OscMessage;
import oscP5.OscP5;

import ch.supermafia.processing.toxiclibs.node.Attractor_node;

import processing.core.PApplet;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;
import toxi.processing.ToxiclibsSupport;

@SuppressWarnings("serial")
public class NodeLines extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 800, P3D);
		smooth();
		stroke(0);
		background(255);
		gfx = new ToxiclibsSupport(this);
		initPhyics();
		jFrameNodeLines = new JFrameNodeLines(this);
		osc = new OscP5(this, PORT);
		colorMode(RGB, 255, 255, 255, 1.0f);
		handX=width/2;
		handY=width/2;
		}
	
	public void draw()
		{
		int t = millis() / 10000;
		t = t % (listParticles.size() - 1);
		fill(0, 0, 0, 0.8f);
		rect(0, 0, width, height);
		strokeWeight(1);
		
		drawNodes(t);
		//drawNodesCircles();
		updatePhysics();
		}
	
	public void mousePressed()
		{
		VerletParticle2D p = new VerletParticle2D(mouseX, mouseY);
		addVerletPart(p);
		}
	
	public void oscEvent(OscMessage message)
		{
		handX = message.get(0).floatValue();
		handY = message.get(1).floatValue();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawNodes(int t)
		{
		stroke(255);
		strokeWeight(5);
		noiseSeed(millis());
		randomSeed(millis());
		fill(105, 112, 222, 1.0f);
		beginShape();
		
		for(int i = 0; i < m; i++)
			{
			int r = (t + (int)random(100)) % listParticles.size();
			vertex(listParticles.get(r).x + random(rand), listParticles.get(r).y + random(rand) * noise(noi));
			int s = (t + (int)random(100)) % listParticles.size();
			vertex(listParticles.get(s).x + random(rand), listParticles.get(s).y + random(rand) * noise(noi));
			}
		endShape();
		}
	
	private void drawNodesCircles()
		{
		for(VerletParticle2D p:listParticles)
			{
			fill(random(50));
			gfx.circle(p, 50);
			}
		}
	
	private void updatePhysics()
		{
		physicWorld.update();
		attractor.updateVerlet();
		//attractor.displayVerlet();
		}
	
	private void addVerletPart(VerletParticle2D p)
		{
		float radius = 10.0f;
		AttractionBehavior2D a = new AttractionBehavior2D(p, radius*2.0f, -1.0f);
		AttractionBehavior2D a2 = new AttractionBehavior2D(p, radius*80.0f, 0.1f);
		listParticles.add(p);
		physicWorld.addBehavior(a);
		physicWorld.addBehavior(a2);
		physicWorld.addParticle(p);
		}
	
	private void initPhyics()
		{
		physicWorld = new VerletPhysics2D();
		physicWorld.setWorldBounds(new Rect(0, 0, width-10, height-10));
		//physicWorld.addBehavior(new GravityBehavior(new Vec2D(0, 0.1f)));
		listParticles = new ArrayList<VerletParticle2D>();
		for(int i = 1; i <= n; i++)
			{
			addVerletPart(new VerletParticle2D(random(width), random(height)));
			}
		attractor = new Attractor_node(new Vec2D(handX, handY), 50, this, physicWorld, gfx);
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
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	public void setM(int m)
		{
		this.m = m;
		}
	
	public void setRand(float rand)
		{
		this.rand = rand;
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private float rand = 25;
	private float noi = 10;
	private int n = 5;
	private int m = 51;
	private VerletPhysics2D physicWorld;
	private ArrayList<VerletParticle2D> listParticles;
	private Attractor_node attractor;
	private ToxiclibsSupport gfx;
	private JFrameNodeLines jFrameNodeLines;
	private OscP5 osc;
	private static final int PORT = 3000;
	private float handX = 0.0f;
	private float handY = 0.0f;
	}
