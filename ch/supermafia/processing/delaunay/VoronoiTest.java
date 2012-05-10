
package ch.supermafia.processing.delaunay;

import java.util.ArrayList;
import java.util.List;

import ch.supermafia.framework3D.geometry.vector.Vec3D;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class VoronoiTest extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(800, 600, P3D);
		smooth();
		addSites();
		}
	
	public void draw()
		{
		background(255);
		drawSites();
		}
	
	public void mousePressed()
		{
		addSites();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void drawSites()
		{
		noStroke();
		for(Vec3D v:sites)
			{
			if (v == clos)
				{
				fill(0, 255, 0);
				}
			else
				{
				fill(255, 0, 0);
				}
			ellipse(v.x(), v.y(), 15, 15);
			}
		
		Vec3D vClosest = closestNeighbourgh(clos);
		fill(0, 0, 255);
		ellipse(vClosest.x(), vClosest.y(), 15, 15);
		noFill();
		stroke(0);
		ellipse((clos.x()+vClosest.x())/2, (clos.y()+vClosest.y())/2, clos.distanceTo(vClosest), clos.distanceTo(vClosest));
		}
	
	private void addSites()
		{
		sites = new ArrayList<Vec3D>();
		
		for(int i = 0; i < N; i++)
			{
			sites.add(new Vec3D(random(width), random(height)));
			}
		clos = sites.get(2);
		}
	
	private Vec3D closestNeighbourgh(Vec3D vSrc)
		{
		float distMin = 100000;
		Vec3D closestNeighbourgh = null;
		for(Vec3D v:sites)
			{
			if (v != vSrc)
				{
				if (vSrc.distanceTo(v) < distMin)
					{
					distMin = vSrc.distanceTo(v);
					closestNeighbourgh = v;
					}
				}
			}
		return closestNeighbourgh;
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private final int N = 20;
	private List<Vec3D> sites;
	private Vec3D clos;
	}
