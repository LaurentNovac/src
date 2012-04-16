
package ch.supermafia.processing.processingParis.threeD;

import processing.core.PApplet;
import unlekker.modelbuilder.UGeometry;
import unlekker.modelbuilder.UNav3D;
import unlekker.modelbuilder.UVertexList;

@SuppressWarnings("serial")
public class Simple3D extends PApplet
	{
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void setup()
		{
		size(600, 600, P3D);
		nav = new UNav3D(this);
		nav.setTranslation(width / 2, height / 2, 0);
		build();
		}
	
	public void draw()
		{
		background(0);
		lights();
		
		nav.doTransforms();
		geo.draw(this);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	private void build()
		{
		outline = new UVertexList[100];
		outline[0] = new UVertexList();
		outline[0].add(-200, 0, -200);
		outline[0].add(200, 0, -200);
		outline[0].add(100, 0, 0);
		outline[0].add(200, 0, 200);
		outline[0].add(-200, 0, 200);
		outline[0].close();
		
		for(int i = 1; i < outline.length; i++)
			{
			outline[i] = new UVertexList(outline[0]);
			outline[i].scale(random(0.5f, 1.5f));
			outline[i].translate(0, i * 100, 0);
			}
		
		geo = new UGeometry();
		
		for(int i = 0; i < outline.length - 1; i++)
			{
			geo.quadStrip(outline[i], outline[i + 1], true);
			}
		
		geo.triangleFan(outline[0], true, true);//second argument is UseCentroid, third is Reverse
		geo.triangleFan(outline[outline.length - 1], true, false);
		geo.writeSTL(this, "Simple3D.stl");
		
		}
	
	public void keyPressed()
		{
		build();
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private UVertexList[] outline;
	private UGeometry geo;
	private UNav3D nav;
	}
