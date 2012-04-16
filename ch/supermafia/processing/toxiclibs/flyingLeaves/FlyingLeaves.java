
package ch.supermafia.processing.toxiclibs.flyingLeaves;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior2D;
import toxi.processing.ToxiclibsSupport;

public class FlyingLeaves extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600);
		stroke(0, 255, 0);
		gfx = new ToxiclibsSupport(this);
		physicWorld = new VerletPhysics2D();
		if (doInteract)
			{
			blownWind = new VerletParticle2D(new Vec2D(0, 0));
			physicWorld.addBehavior(new AttractionBehavior2D(blownWind, 50.0f, -1.0f));
			physicWorld.addParticle(blownWind);
			}
		n = 1000;
		images = new ArrayList<PImage>();
		leaves = new ArrayList<Leaf>(n);
		for(int i = 1; i < 10; i++)
			{
			PImage p = loadImage(RES_DIR + "feuille0" + i + ".png");
			p.resize(p.width / 10, p.height / 10);
			images.add(p);
			}
		for(int i = 1; i <= n; i++)
			{
			Leaf p = new Leaf(new Vec2D(random(width), random(height)), images.get((int)random(9)), this);
			physicWorld.addBehavior(new AttractionBehavior2D(p, 2.5f, 1.0f));
			leaves.add(p);
			}
		for(VerletParticle2D p:leaves)
			{
			physicWorld.addParticle(p);
			}
		}
	
	public void draw()
		{
		if (doInteract)
			{
			blownWind = new VerletParticle2D(new Vec2D(0, 0));
			physicWorld.addBehavior(new AttractionBehavior2D(blownWind, 50.0f, -1.0f));
			physicWorld.addParticle(blownWind);
			doInteract = false;
			}
		Vec2D pos = new Vec2D(mouseX, mouseY);
		if (blownWind != null) blownWind.set(pos);
		background(255);
		ListIterator<Leaf> it = leaves.listIterator();
		while(it.hasNext())
			{
			Leaf p = it.next();
			p.drawMe();
			//gfx.circle(p, 10);
			}
		physicWorld.update();
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'i':
				doInteract=!doInteract;
				break;
			
			default:
				break;
			}
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static ArrayList<Leaf> leaves;
	private static ArrayList<PImage> images;
	private static VerletParticle2D blownWind;
	private static VerletPhysics2D physicWorld;
	private static ToxiclibsSupport gfx;
	private static int n;
	private static final String RES_DIR = "/Users/vacpics/Documents/dev./processing sketch/eclipseWorkspace_/processingSketches/src/ch/supermafia/processing/toxiclibs/flyingLeaves/images/";
	private static boolean doInteract;
	}
