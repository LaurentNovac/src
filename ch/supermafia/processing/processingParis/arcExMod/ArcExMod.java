
package ch.supermafia.processing.processingParis.arcExMod;

import processing.core.PApplet;
import unlekker.modelbuilder.UGeometry;
import unlekker.modelbuilder.UVertexList;

public class ArcExMod extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600, P3D);
		smooth();
		build();
		}
	
	public void draw()
		{
		background(100);
		lights();
		fill(255,0,230);
		UGeometry.drawQuadstrip(this, vl, vl2);		
		}
	
	public void build()
		{
		vl = new UVertexList();
		vl2 = new UVertexList();
		float n = 20;
		for(int i = 0; i < n; i++)
			{
			float t = (float)i / (n - 1);
			vl.add(t * (float)width, random(0.25f, 0.5f) * (float)height,random(-0.15f,0.15f)*(float)width);
			vl2.add(t * (float)width, random(0.75f, 1) * (float)height,random(-0.15f,0.15f)*(float)width);
			}
		}
	
	public void mousePressed()
		{
		build();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private UVertexList vl;
	private UVertexList vl2;
	}
