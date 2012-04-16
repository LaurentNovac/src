
package ch.supermafia.processing.processingParis.ribbon;

import processing.core.PApplet;
import processing.core.PConstants;
import unlekker.modelbuilder.UGeometry;
import unlekker.modelbuilder.UVec3;
import unlekker.modelbuilder.UVertexList;

public class Ribbon
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Ribbon(RibbonSketch parent)
		{
		this.parent = parent;
		vln = 3;
		vl = new UVertexList[vln];
		for(int i = 0; i < vln; i++)
			{
			vl[i] = new UVertexList();
			}
		
		deg = parent.random(PConstants.TWO_PI);
		speed = parent.random(4, 8);
		rotStart = PApplet.radians(parent.random(-2, 2));
		rotEnd = PApplet.radians(parent.random(-2, 2));
		
		pos = new UVec3(0, 0);
		posLast = new UVec3(0, 0);
		
		distTotal = parent.random(0.3f, 0.5f) * (float)parent.width;
		distTravelled = 0;
		radModArray = new float[4];
		radModArray[0] = 0.02f;
		radModArray[1] = parent.random(0.5f, 1.5f)*200;
		radModArray[2] = parent.random(0.5f, 1.5f)*4;
		radModArray[3] = 1;		
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	public void update()
		{
		distT = distTravelled / distTotal;
		if (distT > 1) distT = 1;
		if (distTravelled > distTotal) dead = true;

		posLast.set(pos);
		pos.set(speed, 0);
		pos.add(posLast);
		pos.rotate(rotStart);
		addPoint();
		distTravelled += speed;
		}
	
	public void draw()
		{
		parent.noStroke();
		parent.pushMatrix();
		parent.rotate(deg);
		for(int i = 0; i < vln-1; i++)
			{
			if(i%2==0)
				{
				parent.fill(0);
				UGeometry.drawQuadstrip(parent, vl[i], vl[i+1]);
				}
			else
				{
				parent.fill(255);
				UGeometry.drawQuadstrip(parent, vl[i], vl[i+1]);
				}
			}
		parent.popMatrix();
		}
	
	public void addPoint()
		{
		UVertexList newPt = new UVertexList();
		for(int i = 0; i < vln; i++)
			{
			newPt.add(0, PApplet.map(i, 0, vln - 1, -0.5f, 0.5f));
			}
		newPt.scale(parent.bezierPoint(radModArray[0], radModArray[1], radModArray[2], radModArray[3], distT));
		UVec3 angleV = new UVec3(pos).sub(posLast);
		float angle = angleV.angle2D();
		newPt.rotateZ(angle);
		newPt.translate(pos);
		
		for(int i = 0; i < vln; i++)
			{
			vl[i].add(newPt.v[i]);
			}
		}
	
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	
	public boolean isDead()
		{
		return dead;
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private UVertexList[] vl;
	private float speed;
	private float rad;
	private UVec3 pos;
	private UVec3 posLast;
	private int vln;
	private float rotStart;
	private float rotEnd;
	private RibbonSketch parent;
	private float deg;
	private float distTotal;//total length to travel
	private float distTravelled;//current distance travelled
	private boolean dead;
	private float[] radModArray;
	private float distT;
	}
