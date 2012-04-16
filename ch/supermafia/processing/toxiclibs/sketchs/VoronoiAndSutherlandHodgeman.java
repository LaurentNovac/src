
package ch.supermafia.processing.toxiclibs.sketchs;

import java.util.ArrayList;

import processing.core.PApplet;
import toxi.geom.Polygon2D;
import toxi.geom.Rect;
import toxi.geom.SutherlandHodgemanClipper;
import toxi.geom.Triangle2D;
import toxi.geom.Vec2D;
import toxi.geom.Vec3D;
import toxi.geom.mesh2d.Voronoi;
import toxi.processing.ToxiclibsSupport;
import toxi.util.datatypes.BiasedFloatRange;
import toxi.util.datatypes.FloatRange;

@SuppressWarnings("serial")
public class VoronoiAndSutherlandHodgeman extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		smooth();
		gfx = new ToxiclibsSupport(this);
		voronoi = new Voronoi();
		// focus x positions around horizontal center (w/ 33% standard deviation)
		xPos = new BiasedFloatRange(0, width, width / 2, 0.333f);
		// focus y positions around bottom (w/ 50% standard deviation)
		yPos = new BiasedFloatRange(0, height, height, 0.5f);
		clipper = new SutherlandHodgemanClipper(new Rect(0, 0, width, height));
		pos = new ArrayList<Vec2D>();
		heights = new ArrayList<Vec3D>();
		doShowPoint = true;
		doShowPolygon = true;
		doTriangle = true;
		doTerrain = false;
		doInteract = true;
		//addGridSites();
		}
	
	public void draw()
		{
		background(255);
		noFill();
		stroke(0);
		strokeWeight(5);
		
		if (doTerrain && doInteract)
			{
			translate(0, height-height/3);
			rotateX(405 * 0.01f);
			noLoop();
			}
		//rotateX(mouseY * 0.01f);
		//rotateY(mouseX * 0.01f);
		for(Polygon2D poly:voronoi.getRegions())
			{
			if (doShowPolygon)
				{
				showPolygon(poly);
				clipper.clipPolygon(poly);
				}
			}
		
		gfx.rect(clipper.getBounds());
		
		stroke(0, 0, 255, 50);
		strokeWeight(1);
		if (doTriangle)
			{
			showTesselationTriangle();
			}
		
		stroke(255, 0, 255);
		strokeWeight(2);
		if (doShowPoint)
			{
			showSites();
			}
		stroke(255, 120, 120);
		strokeWeight(3);
		if (doTerrain)
			{
			drawHeightVectors();
			}
		}
	
	public void keyPressed()
		{
		switch(key)
			{
			case 'a':
				voronoi.addPoint(new Vec2D(xPos.pickRandom(), yPos.pickRandom()));
				redraw();
				break;
			case 't':
				doTriangle = !doTriangle;
				break;
			case 'p':
				doShowPolygon = !doShowPolygon;
				break;
			case 'x':
				voronoi = new Voronoi();
				break;
			case 's':
				doShowPoint = !doShowPoint;
				break;
			case 'm':
				doTerrain = !doTerrain;
				break;
			case 'i':
				doInteract = !doInteract;
				break;
			default:
				break;
			}
		}
	
	public void mousePressed()
		{
		Vec2D v = new Vec2D(mouseX, mouseY);
		addSite(v);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void addSite(Vec2D v)
		{
		voronoi.addPoint(v);
		stroke(0, 255, 255);
		pos.add(v);
		}
	
	private void addGridSites()
		{
		for(int i = 1; i < width; i += 100)
			{
			for(int j = 1; j < height; j += 100)
				{
				addSite(new Vec2D(i, j));
				}
			}
		}
	
	private void showSites()
		{
		for(Vec2D p:voronoi.getSites())
			{
			gfx.circle(p, 5);
			}
		}
	
	private void showTesselationTriangle()
		{
		beginShape(LINES);
		for(Triangle2D t:voronoi.getTriangles())
			{
			gfx.triangle(t, false);
			}
		endShape();
		}
	
	private void showPolygon(Polygon2D poly)
		{
		gfx.polygon2D(clipper.clipPolygon(poly));
		}
	
	private void drawHeightVectors()
		{
		beginShape(TRIANGLES);
		//fill(255, 0, 0);
		for(Polygon2D poly:voronoi.getRegions())
			{
			int i = 0;
			for(Vec2D vec:poly.vertices)
				{
				Rect bounds = new Rect(0, 0, width, height);
				if (vec.isInRectangle(bounds) && poly.getCentroid().isInRectangle(bounds))
					{
					if ((i % 2) == 0)
						{
						gfx.vertex(new Vec3D(poly.getCentroid().x(), poly.getCentroid().y(), random((height / 2))));
						gfx.vertex(new Vec3D(vec.x(), vec.y(), 0));
						}
					else
						{
						gfx.vertex(new Vec3D(poly.getCentroid().x(), poly.getCentroid().y(), random((-height / 2))));
						gfx.vertex(new Vec3D(vec.x(), vec.y(), 0));
						}
					i++;
					}
				}
			}
		endShape();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private static Voronoi voronoi;
	private static FloatRange xPos;
	private static FloatRange yPos;
	private static ToxiclibsSupport gfx;
	private static SutherlandHodgemanClipper clipper;
	private static ArrayList<Vec2D> pos;
	private static ArrayList<Vec3D> heights;
	private static boolean doShowPoint;
	private static boolean doShowPolygon;
	private static boolean doTriangle;
	private static boolean doTerrain;
	private static boolean doInteract;
	
	}
